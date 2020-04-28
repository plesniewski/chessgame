package com.whitehatgaming.chessgame.moves

import com.whitehatgaming.chessgame.board.{Board, ClassicBoardRules}
import com.whitehatgaming.chessgame.domain.{Colors, Move, Pawn, Point}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObstaclesTests extends AnyWordSpec with Matchers {

  val boardRules: ClassicBoardRules.type = ClassicBoardRules


  trait WithSimplePawnOnBoard {
    val board: Board = boardRules.emptyBoard.set(Point(2, 2), Pawn(Colors.Black))
    val moves = new DefaultMovesService(PiecesMovesValidations)
  }

  "Checking if way is clear" should {
    "work for horizontal moves" in new WithSimplePawnOnBoard {
      moves.isTheWayClear(board, Move(Point(2, 1), Point(2, 4))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(2, 4), Point(2, 1))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(2, 0), Point(2, 7))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(2, 7), Point(2, 0))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(2, 3), Point(2, 4))) shouldEqual true
      moves.isTheWayClear(board, Move(Point(2, 4), Point(2, 3))) shouldEqual true
    }
    "work for vertical moves" in new WithSimplePawnOnBoard {
      moves.isTheWayClear(board, Move(Point(1, 2), Point(4, 2))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(5, 2), Point(1, 2))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(0, 2), Point(7, 2))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(7, 2), Point(0, 2))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(4, 2), Point(7, 2))) shouldEqual true
      moves.isTheWayClear(board, Move(Point(7, 2), Point(3, 2))) shouldEqual true
    }
    "work for diagonal moves" in new WithSimplePawnOnBoard {
      moves.isTheWayClear(board, Move(Point(0, 0), Point(3, 3))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(4, 4), Point(1, 1))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(1, 3), Point(4, 0))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(4, 0), Point(1, 3))) shouldEqual false
      moves.isTheWayClear(board, Move(Point(2, 2), Point(4, 4))) shouldEqual true
      moves.isTheWayClear(board, Move(Point(3, 3), Point(4, 4))) shouldEqual true
      moves.isTheWayClear(board, Move(Point(7, 0), Point(4, 3))) shouldEqual true
    }
  }

}
