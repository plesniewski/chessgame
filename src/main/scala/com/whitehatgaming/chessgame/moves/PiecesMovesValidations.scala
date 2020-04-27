package com.whitehatgaming.chessgame.moves

import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain.MoveTypes._
import com.whitehatgaming.chessgame.domain._

object PiecesMovesValidations extends MovesValidations with MoveErrors {

  private def isPieceMovingForward(piece: Piece, move: Move) = {
    piece.color == White && move.isUp || piece.color == Black && move.isDown
  }

  private def isMoveRangeValid(piece: Piece, move: Move) = {
    piece.limitedMoveRange.forall(limit => {
      move.cleanRange.exists(_ < limit + 1)
    })
  }

  def validatePieceMove(piece: Piece, move: Move): MoveResult[Unit] = {
    def invalidMove(reason: String = "") = pieceMoveInvalid(piece, reason)

    for {
      _ <- validate(!piece.canMoveOnlyForward || isPieceMovingForward(piece, move), invalidMove("can move only forward"))
      _ <- validate(isMoveRangeValid(piece, move), invalidMove("can't move that far"))
      _ <- move.moveType match {
        case Vertical => validate(piece.canMoveVertically, invalidMove("can't move vertically"))
        case Horizontal => validate(piece.canMoveHorizontally, invalidMove("can't move horizontally"))
        case Diagonal => validate(piece.canMoveDiagonally, invalidMove("can't move diagonally"))
        case Special => validate(piece.specialMoveVectors.contains(move.vector), invalidMove())
      }
    } yield ()
  }

  private def isCaptureRangeValid(piece: Piece, move: Move) = {
    piece.limitedCaptureRange.forall(limit => {
      move.cleanRange.exists(_ < limit + 1)
    })
  }

  def validatePieceCapture(piece: Piece, move: Move): MoveResult[Unit] = {
    val invalidMove = pieceCaptureInvalid(piece)
    for {
      _ <- validate(!piece.canMoveOnlyForward || isPieceMovingForward(piece, move), invalidMove)
      _ <- validate(isCaptureRangeValid(piece, move), invalidMove)
      _ <- move.moveType match {
        case Vertical => validate(piece.capturesVertically, invalidMove)
        case Horizontal => validate(piece.captureHorizontally, invalidMove)
        case Diagonal => validate(piece.captureDiagonally, invalidMove)
        case Special => validate(piece.specialCaptureVectors.contains(move.vector), invalidMove)
      }
    } yield ()
  }
}
