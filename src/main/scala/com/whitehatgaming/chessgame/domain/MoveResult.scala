package com.whitehatgaming.chessgame.domain

import com.whitehatgaming.chessgame.GameError


case class MoveError(message:String) extends GameError(message)

trait MoveErrors {
  val somethingInTheWay: MoveError = MoveError("something in the way")
  def pieceMoveInvalid(piece:Piece): MoveError = MoveError(s"${piece.getClass.getSimpleName} cannot move like that")
  def pieceCaptureInvalid(piece:Piece): MoveError = MoveError(s"${piece.getClass.getSimpleName} cannot capture like that")
}

