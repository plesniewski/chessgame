package com.whitehatgaming.chessgame.views

import com.whitehatgaming.chessgame.board.{Board, BoardRules}
import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain._
import com.whitehatgaming.chessgame.moves.MoveError

import scala.sys.process._

class SimpleTextGameView(boardRules: BoardRules) extends GameView {

  private def clearScreen: Int = {
    "clear".!
  }

  private def drawSquare(x: Int, y: Int, pieceOpt: Option[Piece], checkOpt: Option[PieceOnBoard], moveOpt: Option[Move]): Unit = {
    val point = Point(x, y)
    val c = ((x % 2) + (y % 2)) % 2
    val defaultCol = if (c == 1) Console.WHITE else Console.BLACK
    val defaultBg = if (c == 1) Console.BLACK_B else Console.WHITE_B

    val redPiece = checkOpt.exists(_.point == point) || moveOpt.exists(_.from == point)
    val redBG = (for {
      piece <- pieceOpt
      checkBy <- checkOpt
    } yield piece.isInstanceOf[King] && piece.color != checkBy.piece.color).contains(true) || moveOpt.exists(_.to == point)

    val color = if (redPiece) Console.RED else defaultCol
    val bgColor = if (redBG) Console.RED_B else if (redPiece) Console.CYAN_B else defaultBg

    print(color + bgColor + pieceOpt.map(p => s" $p ").getOrElse("   ") + Console.RESET)
  }

  private lazy val lettersLine =
    Console.WHITE + s"    ${((0 until boardRules.boardColumns).map(_ + 97).map(_.asInstanceOf[Char])).mkString("  ")}" + Console.RESET

  private def rowNumber(y: Int) = {
    Console.WHITE + s" ${(boardRules.boardRows - y)} " + Console.RESET
  }

  private def drawPlayer(number: Int, move: Option[Move], check: Boolean): Unit = {
    val  checkStr = if (check) " Check!" else ""
    println(Console.WHITE + s"    Player $number: ${move.map(m => boardRules.moveString(m) + checkStr ).getOrElse("*")}" + Console.RESET)
  }

  def drawSuccess(board: Board, check: Option[PieceOnBoard], move: Move, currentPlayer: Color): Unit = {
    drawBoard(board, check, move, currentPlayer, markMove = false)
  }


  def drawFailure(board: Board, check: Option[PieceOnBoard], move: Move, currentPlayer: Color, moveError: MoveError): Unit = {
    drawBoard(board, check, move, currentPlayer, markMove = true)
    drawErrorMessage(moveError)
  }

  private def drawBoard(board: Board, check: Option[PieceOnBoard] = None, move: Move, currentPlayer: Color, markMove: Boolean = false): Unit = {
    clearScreen
    drawPlayer(2, Some(move).filter(_ => currentPlayer == White), check.isDefined)
    println
    val array = board.getArray
    val letters = lettersLine
    println(letters)
    for (y <- array.indices) {
      val numberString = rowNumber(y)
      val row = array(y)
      print(numberString)
      for (x <- row.indices) {
        drawSquare(x, y, array(y)(x), check, Some(move).filter(_ => markMove))
      }
      println(numberString)
    }
    println(letters)
    println
    drawPlayer(1, Some(move).filter(_ => currentPlayer == Black), check.isDefined)
  }

  private def drawErrorMessage(error: MoveError): Unit = {
    val by = error.pieceOnBoard.map(p => s" by: ${p.piece.shortSign} on ${boardRules.pointString(p.point)}").getOrElse("")
    println(s"${error.message}$by")
  }
}