package com.whitehatgaming.chessgame

import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain.Rook
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RookMovesValidationTests extends AnyWordSpec with Matchers with MoveTestCommons {

  "moving rook" should {
    "be able to move vertically" in {
      testOfVerticalAnyRangeMoves(Rook(White), testMoveSuccess)
      testOfVerticalAnyRangeMoves(Rook(Black), testMoveSuccess)
    }
    "be able to move horizontally" in {
      testOfHorizontalAnyRangeMoves(Rook(White), testMoveSuccess)
      testOfHorizontalAnyRangeMoves(Rook(Black), testMoveSuccess)
    }
    "be able to move diagonally" in {
      testOfDialonalAnyRangeMoves(Rook(White), testMoveFailure)
      testOfDialonalAnyRangeMoves(Rook(Black), testMoveFailure)
    }
    "not be able to do special crazy stuff" in {
      testOtherVectorsMoves(Rook(White), testMoveFailure)
      testOtherVectorsMoves(Rook(Black), testMoveFailure)
    }
  }
  "capturing rook" should {
    "be able to move vertically" in {
      testOfVerticalAnyRangeMoves(Rook(White), testCaptureSuccess)
      testOfVerticalAnyRangeMoves(Rook(Black), testCaptureSuccess)
    }
    "be able to move horizontally" in {
      testOfHorizontalAnyRangeMoves(Rook(White), testCaptureSuccess)
      testOfHorizontalAnyRangeMoves(Rook(Black), testCaptureSuccess)

    }
    "be able to move diagonally" in {
      testOfDialonalAnyRangeMoves(Rook(White), testCaptureFailure)
      testOfDialonalAnyRangeMoves(Rook(Black), testCaptureFailure)
    }
    "not be able to do special crazy stuff" in {
      testOtherVectorsMoves(Rook(White), testCaptureFailure)
      testOtherVectorsMoves(Rook(Black), testCaptureFailure)
    }
  }

}
