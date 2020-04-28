package com.whitehatgaming.chessgame.moves

import com.whitehatgaming.chessgame.domain.Bishop
import com.whitehatgaming.chessgame.domain.Colors._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BishopMovesValidationTests extends AnyWordSpec with Matchers with MoveTestCommons {

  "moving bishop" should {
    "be able to move vertically" in {
      testOfVerticalAnyRangeMoves(Bishop(White), testMoveFailure)
      testOfVerticalAnyRangeMoves(Bishop(Black), testMoveFailure)
    }
    "be able to move horizontally" in {
      testOfHorizontalAnyRangeMoves(Bishop(White), testMoveFailure)
      testOfHorizontalAnyRangeMoves(Bishop(Black), testMoveFailure)

    }
    "be able to move diagonally" in {
      testOfDialonalAnyRangeMoves(Bishop(White), testMoveSuccess)
      testOfDialonalAnyRangeMoves(Bishop(Black), testMoveSuccess)

    }
    "not be able to move like knight" in {
      testClassicKnightVectorMoves(Bishop(White), testMoveFailure)
      testClassicKnightVectorMoves(Bishop(Black), testMoveFailure)
    }
    "not be able to do special crazy stuff" in {
      testOtherVectorsMoves(Bishop(White), testMoveFailure)
      testOtherVectorsMoves(Bishop(Black), testMoveFailure)
    }
  }
  "capturing bishop" should {
    "be able to move vertically" in {
      testOfHorizontalAnyRangeMoves(Bishop(White), testCaptureFailure)
      testOfHorizontalAnyRangeMoves(Bishop(Black), testCaptureFailure)
    }
    "be able to move horizontally" in {
      testOfHorizontalAnyRangeMoves(Bishop(White), testCaptureFailure)
      testOfHorizontalAnyRangeMoves(Bishop(Black), testCaptureFailure)
    }
    "be able to move diagonally" in {
      testOfDialonalAnyRangeMoves(Bishop(White), testCaptureSuccess)
      testOfDialonalAnyRangeMoves(Bishop(Black), testCaptureSuccess)

    }
    "not be able to move like knight" in {
      testClassicKnightVectorMoves(Bishop(White), testCaptureFailure)
      testClassicKnightVectorMoves(Bishop(Black), testCaptureFailure)
    }
    "not be able to do special crazy stuff" in {
      testOtherVectorsMoves(Bishop(White), testCaptureFailure)
      testOtherVectorsMoves(Bishop(Black), testCaptureFailure)
    }
  }

}
