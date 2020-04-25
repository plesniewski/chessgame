package com.whitehatgaming.chessgame.game


class BoardRules {

  val PawnSpecialLineWhite = 6
  val PawnSpceialLineBlack = 1

  val boardRows = 8
  val boardColumns = 8

  def isPointValid(p:Point):Boolean = p.x > -1 && p.y > -1 && p.x < boardColumns && p.y < boardRows

  

}
