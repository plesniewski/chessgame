package com.whitehatgaming.chessgame

import com.whitehatgaming.chessgame.board.BoardRules
import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain.{Move, Pawn, Piece, Point}
import com.whitehatgaming.chessgame.game. PiecesMovesValidations
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class PawnMovesValidationsTests extends AnyWordSpec with Matchers {


  val boardRules = new BoardRules
  val board = boardRules.initialBoard
  val validations = new PiecesMovesValidations

  private def testMoveSuccess(piece:Piece, from:Point, to:Point) = {
    val res = validations.validatePieceMove(piece, Move(from, to))
    res.isRight shouldEqual true
  }
  private def testCaptureSuccess(piece:Piece, from:Point, to:Point) = {
    val res = validations.validatePieceCapture(piece, Move(from, to))
    res.isRight shouldEqual true
  }

  private def testMoveFailure(piece:Piece, from:Point, to:Point) = {
    val res = validations.validatePieceMove(piece, Move(from, to))
    res.isLeft shouldEqual true
  }

  private def testCaptureFailure(piece:Piece, from:Point, to:Point) = {
    val res = validations.validatePieceCapture(piece, Move(from, to))
    res.isLeft shouldEqual true
  }


  "moving pawn" should {
    "be able to move forward 2 squares if never moved before" in {
      testMoveSuccess(Pawn(White), Point(2,6), Point(2,5))
      testMoveSuccess(Pawn(White), Point(2,6), Point(2,4))
      testMoveSuccess(Pawn(Black), Point(2,1), Point(2,2))
      testMoveSuccess(Pawn(Black), Point(2,1), Point(2,3))
    }
    "be able to move forward 1 square if moved before" in {
      testMoveSuccess(Pawn(White).setMoved(), Point(2,6), Point(2,5))
      testMoveSuccess(Pawn(Black).setMoved(), Point(2,1), Point(2,2))
    }
    "not be able to move forward 2 squares if moved before" in {
      testMoveFailure(Pawn(White).setMoved(), Point(2,6), Point(2,4))
      testMoveFailure(Pawn(Black).setMoved(), Point(2,1), Point(2,3))
    }
    "not be able to move more than 2 squares no matter if moved before" in {
      testMoveFailure(Pawn(White), Point(2,6), Point(2,3))
      testMoveFailure(Pawn(White).setMoved(), Point(2,6), Point(2,3))
      testMoveFailure(Pawn(Black), Point(2,1), Point(2,5))
      testMoveFailure(Pawn(Black).setMoved(), Point(2,1), Point(2,5))
    }
    "not be able to move straight back" in {
      testMoveFailure(Pawn(White).setMoved(), Point(2,5), Point(2,6))
      testMoveFailure(Pawn(White), Point(2,5), Point(2,6))
      testMoveFailure(Pawn(Black).setMoved(), Point(2,1), Point(2,0))
      testMoveFailure(Pawn(Black), Point(2,1), Point(2,0))
    }
    "not be able to move horizontally" in {
      testMoveFailure(Pawn(White).setMoved(), Point(2,5), Point(3,5))
      testMoveFailure(Pawn(White).setMoved(), Point(4,6), Point(3,6))
      testMoveFailure(Pawn(White), Point(2,5), Point(3,5))
      testMoveFailure(Pawn(White), Point(4,6), Point(3,6))

      testMoveFailure(Pawn(Black).setMoved(), Point(2,1), Point(3,1))
      testMoveFailure(Pawn(Black).setMoved(), Point(1,1), Point(2,1))
      testMoveFailure(Pawn(Black), Point(2,1), Point(3,1))
      testMoveFailure(Pawn(Black), Point(1,1), Point(2,1))
    }
    "not be able to move diagonally neither forward or back" in {
      testMoveFailure(Pawn(White).setMoved(), Point(2,5), Point(3,4))
      testMoveFailure(Pawn(White).setMoved(), Point(2,5), Point(3,6))
      testMoveFailure(Pawn(White).setMoved(), Point(2,5), Point(1,4))
      testMoveFailure(Pawn(White).setMoved(), Point(2,5), Point(1,6))

      testMoveFailure(Pawn(White), Point(2,5), Point(3,4))
      testMoveFailure(Pawn(White), Point(2,5), Point(3,6))
      testMoveFailure(Pawn(White), Point(2,5), Point(1,4))
      testMoveFailure(Pawn(White), Point(2,5), Point(1,6))

      testMoveFailure(Pawn(Black).setMoved(), Point(2,1), Point(3,0))
      testMoveFailure(Pawn(Black).setMoved(), Point(2,1), Point(3,2))
      testMoveFailure(Pawn(Black).setMoved(), Point(2,1), Point(1,0))
      testMoveFailure(Pawn(Black).setMoved(), Point(2,1), Point(1,2))

      testMoveFailure(Pawn(Black), Point(2,1), Point(3,0))
      testMoveFailure(Pawn(Black), Point(2,1), Point(3,2))
      testMoveFailure(Pawn(Black), Point(2,1), Point(1,0))
      testMoveFailure(Pawn(Black), Point(2,1), Point(1,2))
    }
  }

  "capturing pawn" should {
    "be able to move diagonally one square" in {
      testCaptureSuccess(Pawn(White), Point(2,6), Point(3,5))
      testCaptureSuccess(Pawn(White).setMoved(), Point(2,6), Point(3,5))
      testCaptureSuccess(Pawn(White), Point(2,6), Point(1,5))
      testCaptureSuccess(Pawn(White).setMoved(), Point(2,6), Point(1,5))

      testCaptureSuccess(Pawn(Black), Point(2,1), Point(3,2))
      testCaptureSuccess(Pawn(Black).setMoved(), Point(2,1), Point(3,2))
      testCaptureSuccess(Pawn(Black), Point(2,1), Point(1,2))
      testCaptureSuccess(Pawn(Black).setMoved(), Point(2,1), Point(1,2))
    }
    "not be able to move diagonally more than one square" in {
      testCaptureFailure(Pawn(White), Point(2,6), Point(4,4))
      testCaptureFailure(Pawn(White), Point(2,6), Point(0,4))
      testCaptureFailure(Pawn(White).setMoved(), Point(2,6), Point(4,4))
      testCaptureFailure(Pawn(White).setMoved(), Point(2,6), Point(0,4))

      testCaptureFailure(Pawn(Black), Point(4,4), Point(6,6))
      testCaptureFailure(Pawn(Black), Point(4,4), Point(6,6))
      testCaptureFailure(Pawn(Black).setMoved(), Point(4,4), Point(6,6))
      testCaptureFailure(Pawn(Black).setMoved(), Point(4,4), Point(6,6))
    }
    "not be able to vertically forward" in {
      testCaptureFailure(Pawn(White).setMoved(), Point(2,6), Point(2,5))
      testCaptureFailure(Pawn(White), Point(2,6), Point(2,5))
      testCaptureFailure(Pawn(White), Point(2,6), Point(2,4))
      testCaptureFailure(Pawn(White), Point(2,6), Point(2,2))

      testCaptureFailure(Pawn(Black).setMoved(), Point(2,1), Point(2,2))
      testCaptureFailure(Pawn(Black), Point(2,1), Point(2,2))
      testCaptureFailure(Pawn(Black), Point(2,1), Point(2,3))
      testCaptureFailure(Pawn(Black), Point(2,1), Point(2,5))
    }

    "not be able to move vertically back" in {
      testCaptureFailure(Pawn(White).setMoved(), Point(2,5), Point(2,6))
      testCaptureFailure(Pawn(White), Point(2,5), Point(2,6))
      testCaptureFailure(Pawn(Black).setMoved(), Point(2,1), Point(2,0))
      testCaptureFailure(Pawn(Black), Point(2,1), Point(2,0))
    }
    "not be able to move diagonally back" in {
      testCaptureFailure(Pawn(White).setMoved(), Point(2,5), Point(3,6))
      testCaptureFailure(Pawn(White).setMoved(), Point(2,5), Point(1,6))
      testCaptureFailure(Pawn(White), Point(2,5), Point(3,6))
      testCaptureFailure(Pawn(White), Point(2,5), Point(1,6))

      testCaptureFailure(Pawn(Black).setMoved(), Point(2,1), Point(3,0))
      testCaptureFailure(Pawn(Black).setMoved(), Point(2,1), Point(1,0))
      testMoveFailure(Pawn(Black), Point(2,1), Point(3,0))
      testMoveFailure(Pawn(Black), Point(2,1), Point(1,0))

    }
    "not be able to move horizontally" in {
      testCaptureFailure(Pawn(White).setMoved(), Point(2,5), Point(3,5))
      testCaptureFailure(Pawn(White).setMoved(), Point(4,6), Point(3,6))
      testCaptureFailure(Pawn(White), Point(2,5), Point(3,5))
      testCaptureFailure(Pawn(White), Point(4,6), Point(3,6))

      testCaptureFailure(Pawn(Black).setMoved(), Point(2,1), Point(3,1))
      testCaptureFailure(Pawn(Black).setMoved(), Point(1,1), Point(2,1))
      testCaptureFailure(Pawn(Black), Point(2,1), Point(3,1))
      testCaptureFailure(Pawn(Black), Point(1,1), Point(2,1))
    }
  }


}
