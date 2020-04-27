package com.whitehatgaming.chessgame

import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain.Queen
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class QueenMovesValidationTests extends AnyWordSpec with Matchers with MoveTestCommons {

  "moving queen" should {
    "be able to move vertically" in {
      testOfVerticalAnyRangeMoves(Queen(White), testMoveSuccess)
      testOfVerticalAnyRangeMoves(Queen(Black), testMoveSuccess)
    }
    "be able to move horizontally" in {
      testOfHorizontalAnyRangeMoves(Queen(White), testMoveSuccess)
      testOfHorizontalAnyRangeMoves(Queen(Black), testMoveSuccess)
    }
    "be able to move diagonally" in {
      testOfDialonalAnyRangeMoves(Queen(White), testMoveSuccess)
      testOfDialonalAnyRangeMoves(Queen(Black), testMoveSuccess)
    }
    "not be able to do special crazy stuff" in {
      testOtherVectorsMoves(Queen(White), testMoveFailure)
      testOtherVectorsMoves(Queen(Black), testMoveFailure)
    }
  }
  "capturing queen" should {
    "be able to move vertically" in {
      testOfHorizontalAnyRangeMoves(Queen(White), testCaptureSuccess)
      testOfHorizontalAnyRangeMoves(Queen(Black), testCaptureSuccess)
    }
    "be able to move horizontally" in {
      testOfHorizontalAnyRangeMoves(Queen(White), testCaptureSuccess)
      testOfHorizontalAnyRangeMoves(Queen(Black), testCaptureSuccess)
    }
    "be able to move diagonally" in {
      testOfDialonalAnyRangeMoves(Queen(White), testCaptureSuccess)
      testOfDialonalAnyRangeMoves(Queen(Black), testCaptureSuccess)
    }
    "not be able to do special crazy stuff" in {
      testOtherVectorsMoves(Queen(White), testCaptureFailure)
      testOtherVectorsMoves(Queen(Black), testCaptureFailure)
    }
  }

}
