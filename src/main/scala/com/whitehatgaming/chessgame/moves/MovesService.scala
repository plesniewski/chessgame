package com.whitehatgaming.chessgame.moves

import com.whitehatgaming.chessgame.board.Board
import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain.{Move, PieceOnBoard}

case class BoardUpdated(board: Board, check: Option[PieceOnBoard])


trait MovesService {
  def processMove(board: Board, move: Move, player: Color, playerInCheck: Boolean): MoveResult[BoardUpdated]
}

