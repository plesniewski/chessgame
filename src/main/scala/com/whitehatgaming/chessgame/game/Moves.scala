package com.whitehatgaming.chessgame.game

import com.whitehatgaming.chessgame.Result
import com.whitehatgaming.chessgame.board.Board
import com.whitehatgaming.chessgame.domain._
import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain.MoveTypes._
import com.whitehatgaming.chessgame.util.ResultUtils._

case class MoveResult(board: Board, inCheckBy:Option[PieceOnBoard], checksBy:Option[PieceOnBoard])

class MovesService(
             validations: PiecesMovesValidations
           ) extends MoveErrors {

  private def anyVerticalObsticles(board:Board, move:Move):Boolean = {
    val ys = Seq(move.from.y, move.to.y).sorted
    board.isColumnOccupied(move.from.x, ys.head + 1, ys.last - 1)
  }

  private def anyHorizontalObsticles(board:Board,move:Move):Boolean = {
    val xs = Seq(move.from.x, move.to.x).sorted
    board.isRowOccupied(move.from.y, xs.head + 1, xs.last - 1)
  }

  def getPiece(board:Board,move:Move, color:Color): Result[Piece] = {
    board.get(move.from).filter(_.color == color).toResult(MoveError(s"$color piece not found on given position"))
  }
  def isCapture(board:Board,move:Move, player:Color):Result[Boolean] = {
    board.get(move.to) match {
      case Some(piece) if piece.color == player => Left(MoveError("Cannot take own player"))
      case Some(_) => Right(true)
      case None => Right(false)
    }
  }

  private def anyDiagonalObsticles(board:Board,move:Move):Boolean = {
    ( for(i <- 1 until move.xSize) yield {
      val point = if (move.from.x < move.to.x) {
        val m = if (move.from.y < move.to.y) i else -i
        Point(move.from.x + i, move.from.y + m)
      } else {
        val m = if (move.to.y < move.from.y) i else -i
        Point(move.to.x + i, move.to.y + m)
      }
      board.isSquareOccupied(point)
    }).contains(true)
  }

    def isTheWayClear(board:Board, move:Move): Boolean = {
      move.isOneSquare ||
        (move.moveType match {
        case Vertical => !anyVerticalObsticles(board, move)
        case Horizontal => !anyHorizontalObsticles(board, move)
        case Diagonal => !anyDiagonalObsticles(board, move)
        case _ => true
      })
   }

  def checkObstacles(board:Board, move:Move): Result[Unit] = {
    validate(isTheWayClear(board, move), MoveError("Can't move, something in the way"))
  }


  def checkCheckOn(board:Board,color:Color):Result[Option[PieceOnBoard]] = {
    board.getKing(color).toResult(new IllegalStateException("King not found")) //should never happen
      .map(king => {
        val kingsColor = king.piece.color
        val kingsPoint = king.point

        board.getOpponets(kingsColor).find({
          case PieceOnBoard(piece, point) =>
            val potentialMove = Move(point, kingsPoint)
            validations.validatePieceCapture(piece, potentialMove).isRight && isTheWayClear(board, potentialMove)
        })
      })


  }


  def processMove(board: Board, move:Move, player:Color, playerInCheck:Boolean):Result[MoveResult] = {
    for {
      piece <- getPiece(board, move, player)
      isCpature <- isCapture(board, move, player)
      _ <- if (isCpature)
        validations.validatePieceCapture(piece, move)
      else
        validations.validatePieceMove(piece, move)
      _ <- checkObstacles(board, move)
      updated <- board.move(move).toResult(new IllegalStateException("Piece not found")) //should never happen
      checkFrom <-checkCheckOn(updated, player)
      checksBy <- if (checkFrom.isEmpty) checkCheckOn(updated, Colors.opponet(player)) else Right(None)
      boardToReturn = if (playerInCheck && checkFrom.isDefined) board else updated
    } yield MoveResult(boardToReturn, checkFrom, checksBy)
  }



}


