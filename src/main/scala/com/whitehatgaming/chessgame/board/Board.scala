package com.whitehatgaming.chessgame.board

import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain._




class Board(array:BoardArray, whites:Seq[PieceOnBoard], blacks:Seq[PieceOnBoard]) {

  def get(point:Point): Option[Piece] = {
    array(point.y)(point.x)
  }

  private def getColumn(n:Int): Array[Option[Piece]] = array.map(_(n))
  private def getRow(n:Int): Array[Option[Piece]] = array(n)

  private def updatedArray(point:Point, value:Option[Piece]): BoardArray = {
    val row = array(point.y)
    array.updated(point.y, row.updated(point.x, value))
  }

  def set(point:Point, piece: Piece):Board = {
    val updated = updatedArray(point, Some(piece))
    val pieceOnBoard = PieceOnBoard(piece, point)

    val (updatedWhites, updatedBlacks) =
    if (piece.color == White) {
      (whites :+ pieceOnBoard, blacks)
    } else
      (whites, blacks :+ pieceOnBoard)
    new Board(array = updated, whites = updatedWhites, blacks = updatedBlacks)
  }


  def move(move: Move):Option[Board] = {
    get(move.from).map(piece => {
      val removed = updatedArray(move.from, None)
      val cutWhites = whites.filterNot(p => p.point == move.from || p.point == move.to)
      val cutBlacks = blacks.filterNot(p => p.point == move.from || p.point == move.to)
      new Board(removed, cutWhites, cutBlacks).set(move.to, piece.setMoved())
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

  def getOpponets(color:Color): Seq[PieceOnBoard] = if (color == White) whites else blacks

  def getKing(color:Color):Option[PieceOnBoard] = {
    (if (color== White) whites else blacks).find(_.piece.isInstanceOf[King])
  }


  def getArray: BoardArray = array


}
