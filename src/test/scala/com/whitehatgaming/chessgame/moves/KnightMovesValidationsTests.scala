package com.whitehatgaming.chessgame.moves

import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain.Knight
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class KnightMovesValidationsTests extends AnyWordSpec with Matchers with MoveTestCommons {

  "moving knight" should {
    "not be able to move vertically" in {
      testOfVerticalAnyRangeMoves(Knight(White), testMoveFailure)
      testOfVerticalAnyRangeMoves(Knight(Black), testMoveFailure)
    }
    "not be able to move horizontally" in {
      testOfHorizontalAnyRangeMoves(Knight(White), testMoveFailure)
      testOfHorizontalAnyRangeMoves(Knight(Black), testMoveFailure)
    }
    "not be able to move diagonally" in {
      testOfDialonalAnyRangeMoves(Knight(White), testMoveFailure)
      testOfDialonalAnyRangeMoves(Knight(Black), testMoveFailure)
    }
    "not be able to move with random vectors" in {
      testOtherVectorsMoves(Knight(White), testMoveFailure)
      testOtherVectorsMoves(Knight(Black), testMoveFailure)
    }
    "be able to move with specific vectors" in {
      testClassicKnightVectorMoves(Knight(White), testMoveSuccess)
      testClassicKnightVectorMoves(Knight(Black), testMoveSuccess)
    }
  }

  "capturing knight" should {
    "not be able to move vertically" in {
      testOfVerticalAnyRangeMoves(Knight(White), testCaptureFailure)
      testOfVerticalAnyRangeMoves(Knight(Black), testCaptureFailure)
    }
    "not be able to move horizontally" in {
      testOfHorizontalAnyRangeMoves(Knight(White), testCaptureFailure)
      testOfHorizontalAnyRangeMoves(Knight(Black), testCaptureFailure)
    }
    "not be able to move diagonally" in {
      testOfDialonalAnyRangeMoves(Knight(White), testCaptureFailure)
      testOfDialonalAnyRangeMoves(Knight(Black), testCaptureFailure)
    }
    "not be able to move with random vectors" in {
      testOtherVectorsMoves(Knight(White), testCaptureFailure)
      testOtherVectorsMoves(Knight(Black), testCaptureFailure)
    }
    "be able to move with specific vectors" in {
      testClassicKnightVectorMoves(Knight(White), testCaptureSuccess)
      testClassicKnightVectorMoves(Knight(Black), testCaptureSuccess)
    }
  }


}
