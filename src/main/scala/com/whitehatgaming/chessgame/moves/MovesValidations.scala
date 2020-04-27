
package com.whitehatgaming.chessgame.moves

import com.whitehatgaming.chessgame.domain.{Move, Piece}

trait MovesValidations {

  def validatePieceMove(piece: Piece, move: Move): MoveResult[Unit]

  def validatePieceCapture(piece: Piece, move: Move): MoveResult[Unit]
}

