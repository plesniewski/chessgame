package com.whitehatgaming.chessgame

import com.whitehatgaming.chessgame.board.{BoardRules, BoardService}
import com.whitehatgaming.chessgame.game.Moves

object ChessGame {

  def main(args: Array[String]): Unit = {

    implicit val boardRules: BoardRules = new BoardRules()

    val boardService = new BoardService()
    val moves = new Moves(boardService)
  }



}
