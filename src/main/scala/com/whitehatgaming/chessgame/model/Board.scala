package com.whitehatgaming.chessgame.model

import com.whitehatgaming.chessgame.model.Colors.Color

object Board {
  def getSpotColor(x:Int, y:Int):Color = Colors(Math.abs((x % 2) + (y % 2)) % 2)
}

class Board {
  val state:BoardArray = Array.ofDim(8)




}
