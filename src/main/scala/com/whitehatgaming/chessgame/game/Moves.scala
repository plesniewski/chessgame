package com.whitehatgaming.chessgame.game

import com.whitehatgaming.chessgame.GameResult
import com.whitehatgaming.chessgame.model.{Bishop, Board, BoardArray, King, Knight, Pawn, Queen, Rook}
import com.whitehatgaming.chessgame.model.Colors._


case class Point(x:Int,y:Int)

case class Move(from:Point, to:Point) {
  def xSize: Int = Math.abs(from.x - to.x)
  def ySize: Int = Math.abs(from.y - to.y)
  def isDiagonal:Boolean = xSize == ySize
  def isVertical:Boolean = from.x == to.x
  def isHorizontal:Boolean = from.y == to.y

  def ascendingY: Seq[Int] = Seq(from.y, to.y).sorted
  def ascendingX: Seq[Int] = Seq(from.x, to.x).sorted

  def isUp:Boolean = from.y > to.y
  def isDown:Boolean = from.y < to.y
}

object Moves {

    val PawnSpecialLineWhite = 6
    val PawnSpceialLineBlack = 1

    def isPawnInSpecialLine(pawn:Pawn, position:Point): Boolean = {
      (pawn.color == White && position.y == PawnSpecialLineWhite) || (pawn.color == Black && position.y == PawnSpceialLineBlack)
    }


    def isSomethingInTheWay(move:Move)(board:Board): Boolean = {
     if (move.isVertical) {
       val ys = Seq(move.from.y, move.to.y).sorted
       val column = board.column(move.from.x).slice(ys.head + 1, ys.last - 1) //+/- 1 as we don't include our position and final postiion which is checked before
       column.exists(_.nonEmpty)
     } else if (move.isHorizontal) {
       val xs = Seq(move.from.x, move.to.x).sorted
       val r = board.row(move.from.y)
         val row = r.slice(xs.head + 1, xs.last - 1)
       row.exists(_.nonEmpty)
     } else if (move.isDiagonal) {
       ( for(i <- 1 until move.xSize) yield {
        val point = if (move.from.x < move.to.x) {
          val m = if (move.from.y < move.to.y) i else -i
          Point(move.from.x + i, move.from.y + m)
        } else {
          val m = if (move.to.y < move.from.y) i else -i
          Point(move.to.x + i, move.to.y + m)
        }
         board.get(point).nonEmpty
       }).contains(true)
     } else false
   }


  //TODO add check
  def validatePawnMove(pawn:Pawn, move:Move)(board: Board):Boolean = {
    val movesForward = (pawn.color == White && move.isUp) || (pawn.color == Black && move.isDown)
    val beatingMove = board.get(move.to).isDefined

    movesForward && (
      if (beatingMove)
        move.ySize == 1 && move.xSize == 1
      else
        if (isPawnInSpecialLine(pawn, move.from))
          move.ySize < 2 && isSomethingInTheWay(move)(board)
        else
          move.ySize == 1
      )
  }


  def validateKingMove(king:King, move:Move)(board: Board):Boolean = {
    Seq(1,2).contains(move.xSize + move.ySize)
  }

  def validateBishopMove(bishop:Bishop, move:Move)(board: Board):Boolean = {
    move.isDiagonal && !isSomethingInTheWay(move)(board)
  }

  def validateQueenMove(queen:Queen, move:Move)(board: Board):Boolean = {
    (move.isDiagonal || move.isHorizontal || move.isVertical) && !isSomethingInTheWay(move)(board)
  }

  def validateRookMove(rook:Rook, move:Move)(board: Board):Boolean = {
    (move.isHorizontal || move.isVertical) && !isSomethingInTheWay(move)(board)
  }

  def validateKnightMove(knight:Knight, move:Move)(board: Board):Boolean = {
    (move.xSize == 1 && move.ySize == 2) || (move.xSize == 2 && move.ySize == 1)
  }



}
