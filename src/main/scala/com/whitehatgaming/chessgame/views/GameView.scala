package com.whitehatgaming.chessgame.views

import com.whitehatgaming.chessgame.board.Board
import com.whitehatgaming.chessgame.domain.Colors.Color
import com.whitehatgaming.chessgame.domain.{Move, PieceOnBoard}
import com.whitehatgaming.chessgame.moves.MoveError


trait GameView {

  def drawBoard(board: Board, check: Option[PieceOnBoard] = None, move: Move, currentPlayer: Color, markMove: Boolean = false): Unit

  def drawErrorMessage(error: MoveError): Unit
}


