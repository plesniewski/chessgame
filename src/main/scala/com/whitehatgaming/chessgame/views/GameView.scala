package com.whitehatgaming.chessgame.views

import com.whitehatgaming.chessgame.board.Board
import com.whitehatgaming.chessgame.domain.Colors.Color
import com.whitehatgaming.chessgame.domain.{Move, PieceOnBoard}
import com.whitehatgaming.chessgame.moves.MoveError


trait GameView {

  def drawSuccess(board: Board, check: Option[PieceOnBoard] = None, move: Move, currentPlayer: Color): Unit

  def drawFailure(board: Board, check: Option[PieceOnBoard] = None, move: Move, currentPlayer: Color, moveError: MoveError): Unit
}


