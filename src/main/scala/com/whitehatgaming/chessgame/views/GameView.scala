package com.whitehatgaming.chessgame.views

import com.whitehatgaming.chessgame.board.Board
import com.whitehatgaming.chessgame.domain. PieceOnBoard


trait GameView {

  def drawBoard(board: Board, check:Option[PieceOnBoard] = None):Unit

}


