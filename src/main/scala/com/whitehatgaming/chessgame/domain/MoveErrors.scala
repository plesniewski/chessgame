package com.whitehatgaming.chessgame.domain

import com.whitehatgaming.chessgame.GameError


case class MoveError(message:String) extends GameError

trait MoveErrors {
  val somethingInTheWay: MoveError = MoveError("something in the way")
  val cannotCaptureOwnPiece: MoveError = MoveError("Cannot capture own piece")
  def pieceMoveInvalid(piece:Piece, reason:String = ""): MoveError = MoveError(s"${piece.getClass.getSimpleName} cannot move like that${if (reason.nonEmpty) s": $reason" else ""}")
  def pieceCaptureInvalid(piece:Piece): MoveError = MoveError(s"${piece.getClass.getSimpleName} cannot capture like that")
}

