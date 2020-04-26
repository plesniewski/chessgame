package com.whitehatgaming.chessgame.board

import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain._


class BoardService()(implicit boardRules:BoardRules) {


  private val array:BoardArray = boardRules.initialBoardArray

  var whites:Seq[PieceOnBoard] = Nil
  var blacks:Seq[PieceOnBoard] = Nil

  def get(point:Point): Option[Piece] = {
    array(point.y)(point.x)
  }

  private def getColumn(n:Int): Array[Option[Piece]] = array.map(_(n))
  private def getRow(n:Int): Array[Option[Piece]] = array(n)

  private def updateArray(point:Point, value:Option[Piece]): Unit = {
    val row = array(point.y)
    array.update(point.y, row.updated(point.x, value))
  }

  def set(point:Point, piece: Piece):Unit = {
    updateArray(point, Some(piece))
    val pieceOnBoard = PieceOnBoard(piece, point)

    if (piece.color == White)
      whites :+= pieceOnBoard
    else
      blacks :+= pieceOnBoard
  }

  def move(move: Move):Unit = {
    get(move.from).foreach(piece => {
      updateArray(move.from, None)
      whites = whites.filterNot(p => p.point == move.from || p.point == move.to)
      blacks = blacks.filterNot(p => p.point == move.from || p.point == move.to)
      set(move.to, piece.setMoved())
    })
  }

  def isColumnOccupied(colNo:Int, from:Int, until:Int):Boolean = {
    getColumn(colNo).slice(from, until).exists(_.nonEmpty)
  }

  def isRowOccupied(rowNo:Int, from:Int, until:Int): Boolean = {
    getRow(rowNo).slice(from, until).exists(_.nonEmpty)
  }

  def isSquareOccupied(point: Point):Boolean = {
    get(point).nonEmpty
  }

  def getWhitePieces: Seq[PieceOnBoard] = whites
  def getBlackPieces: Seq[PieceOnBoard] = blacks

  def printBoard:Unit = {
    array.foreach(r => println(r.map(i => if (i.isDefined) 1 else 0 ).mkString(" ")))
  }
}
