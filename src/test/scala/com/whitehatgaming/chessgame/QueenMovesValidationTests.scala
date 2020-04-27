package com.whitehatgaming.chessgame

import com.whitehatgaming.chessgame.domain.Colors.White
import com.whitehatgaming.chessgame.domain.{Pawn, Point, Queen}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class QueenMovesValidationTests  extends AnyWordSpec with Matchers with MoveTestCommons{

  "moving queen" should {
    "be able to move vertically" in {
      testMoveSuccess(Queen(White), Point(4,6), Point(4,5))
      testMoveSuccess(Queen(White), Point(2,0), Point(2,7))
      testMoveSuccess(Queen(White), Point(2,7), Point(2,0))
    }
    "be able to move horizontally" in {
      testMoveSuccess(Queen(White), Point(6,4), Point(5,4))
      testMoveSuccess(Queen(White), Point(0,2), Point(7,2))
      testMoveSuccess(Queen(White), Point(7,4), Point(0,4))
    }
    "be able to move diagonally" in {
      testMoveSuccess(Queen(White), Point(6,4), Point(4,6))
      testMoveSuccess(Queen(White), Point(4,6), Point(6,4))
      testMoveSuccess(Queen(White), Point(0,0), Point(7,7))
      testMoveSuccess(Queen(White), Point(7,1), Point(1,7))
      testMoveSuccess(Queen(White), Point(7,0), Point(0,7))
      testMoveSuccess(Queen(White), Point(0,7), Point(7,0))
    }
    "not be able to do special crazy stuff" in {
      testMoveFailure(Queen(White), Point(1,0), Point(2,2))
      testMoveFailure(Queen(White), Point(3,0), Point(4,7))
      testMoveFailure(Queen(White), Point(3,7), Point(4,0))
    }
  }
  "capturing queen" should {
    "be able to move vertically" in {
      testCaptureSuccess(Queen(White), Point(4,6), Point(4,5))
      testCaptureSuccess(Queen(White), Point(2,0), Point(2,7))
      testCaptureSuccess(Queen(White), Point(2,7), Point(2,0))
    }
    "be able to move horizontally" in {
      testCaptureSuccess(Queen(White), Point(6,4), Point(5,4))
      testCaptureSuccess(Queen(White), Point(0,2), Point(7,2))
      testCaptureSuccess(Queen(White), Point(7,4), Point(0,4))
    }
    "be able to move diagonally" in {
      testCaptureSuccess(Queen(White), Point(6,4), Point(4,6))
      testCaptureSuccess(Queen(White), Point(4,6), Point(6,4))
      testCaptureSuccess(Queen(White), Point(0,0), Point(7,7))
      testCaptureSuccess(Queen(White), Point(7,1), Point(1,7))
      testCaptureSuccess(Queen(White), Point(7,0), Point(0,7))
      testCaptureSuccess(Queen(White), Point(0,7), Point(7,0))
    }
    "not be able to do special crazy stuff" in {
      testCaptureFailure(Queen(White), Point(1,0), Point(2,2))
      testCaptureFailure(Queen(White), Point(3,0), Point(4,7))
      testCaptureFailure(Queen(White), Point(3,7), Point(4,0))
    }
  }

}
