package com.whitehatgaming.chessgame.game

import com.whitehatgaming.chessgame._
import com.whitehatgaming.chessgame.board. BoardService
import com.whitehatgaming.chessgame.util.ResultUtils._
import com.whitehatgaming.chessgame.domain._
import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain.MoveTypes._


class Moves(boardService: BoardService) extends MoveErrors {

  private def anyVerticalObsticles(move:Move):Boolean = {
    val ys = Seq(move.from.y, move.to.y).sorted
    boardService.isColumnOccupied(move.from.x, ys.head + 1, ys.last - 1)
  }

  private def anyHorizontalObsticles(move:Move):Boolean = {
    val xs = Seq(move.from.x, move.to.x).sorted
    boardService.isRowOccupied(move.from.y, xs.head + 1, xs.last - 1)
  }

  private def anyDiagonalObsticles(move:Move):Boolean = {
    ( for(i <- 1 until move.xSize) yield {
      val point = if (move.from.x < move.to.x) {
        val m = if (move.from.y < move.to.y) i else -i
        Point(move.from.x + i, move.from.y + m)
      } else {
        val m = if (move.to.y < move.from.y) i else -i
        Point(move.to.x + i, move.to.y + m)
      }
      boardService.isSquareOccupied(point)
    }).contains(true)
  }

    def isTheWayClear(move:Move): Boolean = {
      move.isOneSquare ||
        (move.moveType match {
        case Vertical => !anyVerticalObsticles(move)
        case Horizontal => !anyHorizontalObsticles(move)
        case Diagonal => !anyDiagonalObsticles(move)
        case _ => true
      })

   }

  private def isPieceMovingForward(piece:Piece, move:Move) = {
    piece.color == White && move.isUp || piece.color == Black && move.isDown
  }

  private def isMoveRangeValid(piece:Piece, move:Move) = {
    piece.limitedMoveRange.forall(limit => {
      move.cleanRange.exists(_ < limit + 1)
    })
  }

  def validatePieceMove(piece:Piece, move:Move):Result[Unit] = {
    val invalidMove = pieceMoveInvalid(piece)

    for {
      _ <- validate(!piece.canMoveOnlyForward || isPieceMovingForward(piece, move), invalidMove)
      _ <- validate(isMoveRangeValid(piece, move), invalidMove)
      _ <- move.moveType match {
        case Vertical => validate(piece.canMoveVertically, invalidMove)
        case Horizontal => validate(piece.canMoveHorizontally, invalidMove)
        case Diagonal => validate(piece.canMoveDiagonally, invalidMove)
        case Special => validate(piece.specialMoveVectors.contains(move.vector), invalidMove)
      }
    } yield ()
  }

  private def isCaptureRangeValid(piece:Piece, move:Move) = {
    piece.limitedCaptureRange.forall(limit => {
      move.cleanRange.exists(_ < limit + 1)
    })
  }

  def validatePieceCapture(piece:Piece, move:Move):Result[Unit] = {
    val invalidMove = pieceCaptureInvalid(piece)
    for {
      _ <- validate(!piece.canMoveOnlyForward || isPieceMovingForward(piece, move), invalidMove)
      _ <- validate(isCaptureRangeValid(piece, move), invalidMove)
      _ <- move.moveType match {
        case Vertical => validate(piece.capturesVertically, invalidMove)
        case Horizontal => validate(piece.captureHorizontally, invalidMove)
        case Diagonal => validate(piece.captureDiagonally, invalidMove)
        case Special => validate(piece.specialCaptureVectors.contains(move.vector), invalidMove)
      }
    } yield ()
  }

  def checkCheckOn(king:PieceOnBoard):Option[PieceOnBoard] = {
    val kingsColor = king.piece.color
    val kingsPoint = king.point
    val possibleOpponets = (if (kingsColor == White) boardService.getBlackPieces  else boardService.getWhitePieces)

    possibleOpponets.find({
      case PieceOnBoard(piece, point) =>
        val potentialMove = Move(point, kingsPoint)
        validatePieceCapture(piece, potentialMove).isRight && isTheWayClear(potentialMove)
    })
  }


}
