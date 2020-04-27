package com.whitehatgaming.chessgame

import com.whitehatgaming.chessgame.board.{Board, ClassicBoardRules}
import com.whitehatgaming.chessgame.domain.{Move, Piece, Point}
import com.whitehatgaming.chessgame.moves.{MovesValidations, PiecesMovesValidations}
import org.scalatest.Assertion
import org.scalatest.matchers.should.Matchers

trait MoveTestCommons {
  require: Matchers =>
  val board: Board = ClassicBoardRules.initialBoard
  val validations: MovesValidations = PiecesMovesValidations

  protected def testMoveSuccess(piece: Piece, from: Point, to: Point): Assertion = {
    val res = validations.validatePieceMove(piece, Move(from, to))
    res.isRight shouldEqual true
  }

  protected def testCaptureSuccess(piece: Piece, from: Point, to: Point): Assertion = {
    val res = validations.validatePieceCapture(piece, Move(from, to))
    res.isRight shouldEqual true
  }

  protected def testMoveFailure(piece: Piece, from: Point, to: Point): Assertion = {
    val res = validations.validatePieceMove(piece, Move(from, to))
    res.isLeft shouldEqual true
  }

  protected def testCaptureFailure(piece: Piece, from: Point, to: Point): Assertion = {
    val res = validations.validatePieceCapture(piece, Move(from, to))
    res.isLeft shouldEqual true
  }

  protected def testOfVerticalAnyRangeMoves(piece: Piece, test: (Piece, Point, Point) => Assertion): Assertion = {
    test(piece, Point(4, 6), Point(4, 5))
    test(piece, Point(2, 0), Point(2, 7))
    test(piece, Point(2, 7), Point(2, 0))
  }

  protected def testOfHorizontalAnyRangeMoves(piece: Piece, test: (Piece, Point, Point) => Assertion): Assertion = {
    test(piece, Point(6, 4), Point(5, 4))
    test(piece, Point(0, 2), Point(7, 2))
    test(piece, Point(7, 4), Point(0, 4))
  }

  protected def testOfDialonalAnyRangeMoves(piece: Piece, test: (Piece, Point, Point) => Assertion): Assertion = {
    test(piece, Point(6, 4), Point(4, 6))
    test(piece, Point(4, 6), Point(6, 4))
    test(piece, Point(0, 0), Point(7, 7))
    test(piece, Point(7, 1), Point(1, 7))
    test(piece, Point(7, 0), Point(0, 7))
    test(piece, Point(0, 7), Point(7, 0))
  }

  protected def testOtherVectorsMoves(piece: Piece, test: (Piece, Point, Point) => Assertion): Assertion = {
    test(piece, Point(1, 0), Point(2, 2))
    test(piece, Point(3, 0), Point(4, 7))
    test(piece, Point(3, 7), Point(4, 0))
  }

}
