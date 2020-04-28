package com.whitehatgaming.chessgame.moves

import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain.King
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class KingMovesValidationsTests extends AnyWordSpec with Matchers with MoveTestCommons {

  "moving king" should {
    "be able to move vertically one square" in {
      testOfVerticalOneRangeMoves(King(White), testMoveSuccess)
      testOfVerticalOneRangeMoves(King(Black), testMoveSuccess)
    }
    "be able to move horizontally one square" in {
      testOfHorizontalOneRangeMoves(King(White), testMoveSuccess)
      testOfHorizontalOneRangeMoves(King(Black), testMoveSuccess)
    }
    "be able to move diagonally one square" in {
      testOfDiagonalOneRangeMoves(King(White), testMoveSuccess)
      testOfDiagonalOneRangeMoves(King(Black), testMoveSuccess)
    }
    "not be able to move vertically more than one square" in {
      testOfVerticalMoreThanOneRangeMoves(King(White), testMoveFailure)
      testOfVerticalMoreThanOneRangeMoves(King(Black), testMoveFailure)
    }
    "not be able to move horizontally more than one square" in {
      testOfHorizontalMoreThanOneRangeMoves(King(White), testMoveFailure)
      testOfHorizontalMoreThanOneRangeMoves(King(Black), testMoveFailure)
    }
    "not be able to move diagonally more than one square" in {
      testDiagonalMoreThanOneRangeMoves(King(White), testMoveFailure)
      testDiagonalMoreThanOneRangeMoves(King(Black), testMoveFailure)
    }
    "not be able to move by random vectors" in {
      testOtherVectorsMoves(King(White), testMoveFailure)
      testOtherVectorsMoves(King(Black), testMoveFailure)
    }
    "not be able to move like knight" in {
      testClassicKnightVectorMoves(King(White), testMoveFailure)
      testClassicKnightVectorMoves(King(Black), testMoveFailure)
    }
  }

  "capturing king" should {
    "be able to move vertically one square" in {
      testOfVerticalOneRangeMoves(King(White), testCaptureSuccess)
      testOfVerticalOneRangeMoves(King(Black), testCaptureSuccess)
    }
    "be able to move horizontally one square" in {
      testOfHorizontalOneRangeMoves(King(White), testCaptureSuccess)
      testOfHorizontalOneRangeMoves(King(Black), testCaptureSuccess)
    }
    "be able to move diagonally one square" in {
      testOfDiagonalOneRangeMoves(King(White), testCaptureSuccess)
      testOfDiagonalOneRangeMoves(King(Black), testCaptureSuccess)
    }
    "not be able to move vertically more than one square" in {
      testOfVerticalMoreThanOneRangeMoves(King(White), testCaptureFailure)
      testOfVerticalMoreThanOneRangeMoves(King(Black), testCaptureFailure)
    }
    "not be able to move horizontally more than one square" in {
      testOfHorizontalMoreThanOneRangeMoves(King(White), testCaptureFailure)
      testOfHorizontalMoreThanOneRangeMoves(King(Black), testCaptureFailure)
    }
    "not be able to move diagonally more than one square" in {
      testDiagonalMoreThanOneRangeMoves(King(White), testCaptureFailure)
      testDiagonalMoreThanOneRangeMoves(King(Black), testCaptureFailure)
    }
    "not be able to move by random vectors" in {
      testOtherVectorsMoves(King(White), testCaptureFailure)
      testOtherVectorsMoves(King(Black), testCaptureFailure)
    }
    "not be able to move like knight" in {
      testClassicKnightVectorMoves(King(White), testCaptureFailure)
      testClassicKnightVectorMoves(King(Black), testCaptureFailure)
    }
  }


}
