package com.whitehatgaming.chessgame.domain
import com.whitehatgaming.chessgame.domain.MoveTypes._

case class Move(from:Point, to:Point) {
  def xSize: Int = Math.abs(from.x - to.x)
  def ySize: Int = Math.abs(from.y - to.y)

  val moveType: MoveType =
    if (xSize == ySize)
      Diagonal
    else
    if (from.x == to.x)
      Vertical
    else
      if (from.y == to.y)
      Horizontal
    else
      Special

  def ascendingY: Seq[Int] = Seq(from.y, to.y).sorted
  def ascendingX: Seq[Int] = Seq(from.x, to.x).sorted

  def isUp:Boolean = from.y > to.y
  def isDown:Boolean = from.y < to.y
  def isOneSquare:Boolean = xSize < 2 && ySize < 2

  def vector:(Int,Int) = (to.x - from.x, to.y - from.y)

  def cleanRange:Option[Int] =
    Some(if (xSize == 0) ySize else if (ySize == 0) xSize else xSize).filterNot(_ => moveType == Special)
}
