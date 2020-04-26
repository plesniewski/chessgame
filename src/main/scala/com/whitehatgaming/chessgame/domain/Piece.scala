package com.whitehatgaming.chessgame.domain

import com.whitehatgaming.chessgame.domain.Colors.Color


sealed trait CapturesVertically
sealed trait CapturesDiagonally
sealed trait CapturesHorizontally
sealed trait MovesOnlyForward
sealed trait MovesVertically
sealed trait MovesDiagonally
sealed trait MovesHorizontally
sealed trait MovesOneSquare

trait CapturesAllTheWay extends CapturesVertically with CapturesHorizontally with CapturesDiagonally
trait MovesAllTheWay extends MovesDiagonally with MovesHorizontally with MovesVertically


trait Piece {
  def color:Color
  def capturesVertically:Boolean = isInstanceOf[CapturesVertically]
  def captureHorizontally:Boolean = isInstanceOf[CapturesHorizontally]
  def captureDiagonally:Boolean = isInstanceOf[CapturesDiagonally]
  def canMoveOnlyForward:Boolean = isInstanceOf[MovesOnlyForward]
  def canMoveVertically:Boolean = isInstanceOf[MovesVertically]
  def canMoveHorizontally:Boolean = isInstanceOf[MovesHorizontally]
  def canMoveDiagonally:Boolean = isInstanceOf[MovesDiagonally]

  
  def specialMoveVectors:Seq[(Int, Int)] = Nil
  def specialCaptureVectors:Seq[(Int, Int)] = Nil
  var neverMoved = true
  def setMoved():Piece = {
    neverMoved = false
    this
  }

  def limitedMoveRange:Option[Int] = None
  def limitedCaptureRange:Option[Int] = None


}


case class Pawn(color:Color) extends Piece with MovesOnlyForward with MovesHorizontally with CapturesVertically {
  override def limitedMoveRange: Option[Int] = if (neverMoved) Some(2) else Some(1)
  override def limitedCaptureRange:Option[Int] = Some(1)
}

case class King(color:Color) extends Piece with CapturesAllTheWay with MovesAllTheWay {
  override def limitedMoveRange:Option[Int] = Some(1)
  override def limitedCaptureRange:Option[Int] = Some(1)
}

case class Bishop(color:Color) extends Piece with CapturesDiagonally with MovesDiagonally


case class Queen(color:Color) extends Piece with CapturesAllTheWay with MovesAllTheWay

case class Rook(color:Color) extends Piece with CapturesVertically with CapturesHorizontally with MovesHorizontally with MovesVertically

case class Knight(color:Color) extends Piece {
  override val specialMoveVectors: Seq[(Int, Int)] = Seq((1,2),(-1, 2),(1, -2), (-1, -2), (2, 1), (-2, 1), (2, -1), (-2, -1))
  override val specialCaptureVectors: Seq[(Int, Int)] = specialMoveVectors

}


