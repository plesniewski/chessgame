package com.whitehatgaming.chessgame

import com.whitehatgaming.chessgame.board.{Board, BoardRules}
import com.whitehatgaming.chessgame.domain.{Move, Piece, Point}
import com.whitehatgaming.chessgame.moves.{MovesValidations, PiecesMovesValidations}
import org.scalatest.Assertion
import org.scalatest.matchers.should.Matchers

trait MoveTestCommons {
  require: Matchers =>
  val boardRules = new BoardRules
  val board: Board = boardRules.initialBoard
  val validations: MovesValidations =  PiecesMovesValidations

  protected def testMoveSuccess(piece:Piece, from:Point, to:Point): Assertion = {
    val res = validations.validatePieceMove(piece, Move(from, to))
    res.isRight shouldEqual true
  }
  protected def testCaptureSuccess(piece:Piece, from:Point, to:Point): Assertion = {
    val res = validations.validatePieceCapture(piece, Move(from, to))
    res.isRight shouldEqual true
  }

  protected def testMoveFailure(piece:Piece, from:Point, to:Point): Assertion = {
    val res = validations.validatePieceMove(piece, Move(from, to))
    res.isLeft shouldEqual true
  }

  protected def testCaptureFailure(piece:Piece, from:Point, to:Point): Assertion = {
    val res = validations.validatePieceCapture(piece, Move(from, to))
    res.isLeft shouldEqual true
  }

}
