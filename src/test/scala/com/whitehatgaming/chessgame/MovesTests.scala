package com.whitehatgaming.chessgame

import com.whitehatgaming.chessgame.game.{Move, Moves, Point}
import com.whitehatgaming.chessgame.model.{Board, BoardArray, Colors, Pawn, Piece}
import org.scalatest.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MovesTests extends AnyWordSpec with Matchers {




  def printBoard(b:Board) = {
    b.array.foreach(r => println(r.map(i => if (i.isDefined) 1 else 0 ).mkString(" ")))
  }

  "test" should {
    "me" in {
      val board = Board.empty
      board.set(Point(2,2), Some(Pawn(Colors.White)))

      printBoard(board)

      Moves.isSomethingInTheWay(Move(Point(2,1), Point(2,4)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(2,4), Point(2,1)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(2,0), Point(2,7)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(2,7), Point(2,0)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(2,3), Point(2,4)))(board) shouldEqual false
      Moves.isSomethingInTheWay(Move(Point(2,4), Point(2,3)))(board) shouldEqual false

      Moves.isSomethingInTheWay(Move(Point(1,2), Point(4,2)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(5,2), Point(1,2)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(0,2), Point(7,2)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(7,2), Point(0,2)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(4,2), Point(7,2)))(board) shouldEqual false
      Moves.isSomethingInTheWay(Move(Point(7,2), Point(3,2)))(board) shouldEqual false


      Moves.isSomethingInTheWay(Move(Point(0,0), Point(3,3)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(4,4), Point(1,1)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(1,3), Point(4,0)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(4,0), Point(1,3)))(board) shouldEqual true
      Moves.isSomethingInTheWay(Move(Point(2,2), Point(4,4)))(board) shouldEqual false
      Moves.isSomethingInTheWay(Move(Point(3,3), Point(4,4)))(board) shouldEqual false
      Moves.isSomethingInTheWay(Move(Point(7,0), Point(4,3)))(board) shouldEqual false

    }
  }

}
