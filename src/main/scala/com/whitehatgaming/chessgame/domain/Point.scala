package com.whitehatgaming.chessgame.domain


case class Point(x:Int,y:Int) {

  def inc(xi:Int, yi:Int):Point = copy(x = x + xi, y = y + yi)

}
