package com.whitehatgaming.chessgame.moves

import com.whitehatgaming.chessgame.domain.{Piece, PieceOnBoard}


case class MoveError(message: String, pieceOnBoard: Option[PieceOnBoard] = None) extends MoveFailure

case class GameError(message: String) extends MoveFailure

trait MoveErrors {
  val somethingInTheWay: MoveError = MoveError("something in the way")
  val cannotCaptureOwnPiece: MoveError = MoveError("Cannot capture own piece")

  def cantMoveBecauseOfCheck(by: PieceOnBoard): MoveError = MoveError("Cannot move like that, because of check", Some(by))

  def pieceMoveInvalid(piece: Piece, reason: String = ""): MoveError = MoveError(s"${piece.getClass.getSimpleName} cannot move like that${if (reason.nonEmpty) s": $reason" else ""}")

  def pieceCaptureInvalid(piece: Piece): MoveError = MoveError(s"${piece.getClass.getSimpleName} cannot capture like that")
}

