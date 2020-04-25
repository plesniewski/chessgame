package com.whitehatgaming.chessgame.model

import com.whitehatgaming.chessgame.game.Point
import com.whitehatgaming.chessgame.model.Colors.Color

object Board {
  def getSpotColor(x:Int, y:Int):Color = Colors(Math.abs((x % 2) + (y % 2)) % 2)

  def empty:Board = {
    val emptyRow:Array[Option[Piece]] = Array(None,None,None,None,None,None,None,None)
    new Board(Array(emptyRow, emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow))
  }

}




class Board(val array:BoardArray) {

  def get(point:Point): Option[Piece] = {
    array(point.y)(point.x)
  }

  def column(n:Int): Array[Option[Piece]] = array.map(_(n))
  def row(n:Int): Array[Option[Piece]] = array(n)

  def set(point:Point, value: Option[Piece]):Unit = {
    val row = array(point.y)
    array.update(point.y, row.updated(point.x, value))
  }



}
