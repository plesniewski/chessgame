package com.whitehatgaming.chessgame.domain

case class Point(x:Int,y:Int) {

  def inc(xi:Int, yi:Int):Point = copy(x = x + xi, y = y + yi)

  def isValid(boardSizeX:Int=8, boardSizeY:Int = 8):Boolean = {
    x > 0 && y > 0&& x< boardSizeX && y < boardSizeY
  }
}
