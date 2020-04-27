package com.whitehatgaming.chessgame.views

import com.whitehatgaming.chessgame.board.Board
import com.whitehatgaming.chessgame.domain.{ King, PieceOnBoard, Point}

object SimpleTextGameView extends GameView {
  
  def drawBoard(board:Board, check:Option[PieceOnBoard] = None): Unit = {
    val array = board.getArray
    val lettersString =  Console.WHITE + s"    ${(array.head.indices.map(_ + 97).map(_.asInstanceOf[Char])).mkString("  ")}" + Console.RESET
    println(lettersString)
    for (y <- array.indices) {
      val numberString =  Console.WHITE + s" ${(array.length - y)} " + Console.RESET
      val row = array(y)
      print(numberString)
      for (x <- row.indices) {
        val c = ((x % 2) + (y % 2)) % 2
        val defaultCol = if (c == 1) Console.WHITE  else Console.BLACK
        val defaultBg = if (c == 1) Console.BLACK_B else Console.WHITE_B
        val pieceOpt = array(y)(x)
        val (col, bg) = (for {
          piece <- pieceOpt
          checkBy <- check
        } yield {
          if (checkBy.point == Point(x, y))
            Some((defaultCol, Console.RED_B))
          else
          if (piece.isInstanceOf[King] && piece.color != checkBy.piece.color)
            Some((Console.RED, defaultBg))
          else None
        }).flatten.getOrElse((defaultCol, defaultBg))
        print(col + bg + pieceOpt.map(p => s" $p ").getOrElse("   ") + Console.RESET)
      }
      println(numberString)
    }
    println(lettersString)
  }
  
}