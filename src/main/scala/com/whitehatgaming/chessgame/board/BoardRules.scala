package com.whitehatgaming.chessgame.board

import com.whitehatgaming.chessgame.domain.{BoardArray, Piece, Point}

class BoardRules {


  val boardRows = 8
  val boardColumns = 8

  def isPointValid(p:Point):Boolean = p.x > -1 && p.y > -1 && p.x < boardColumns && p.y < boardRows

  def emptyBoardArray: BoardArray = {
    val emptyRow:Array[Option[Piece]] = Array(None,None,None,None,None,None,None,None)
    Array(emptyRow, emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow)
  }

  def initialBoardArray: BoardArray = emptyBoardArray

}
