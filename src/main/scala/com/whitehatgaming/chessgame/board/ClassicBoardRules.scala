package com.whitehatgaming.chessgame.board

import com.whitehatgaming.chessgame.domain.Colors._
import com.whitehatgaming.chessgame.domain._

object ClassicBoardRules extends BoardRules {

  val boardRows = 8
  val boardColumns = 8

  def isPointValid(p: Point): Boolean = p.x > -1 && p.y > -1 && p.x < boardColumns && p.y < boardRows

  def isMoveValid(m: Move): Boolean = isPointValid(m.from) && isPointValid(m.to)

  private val emptyRow: Array[Option[Piece]] = Array.fill(boardColumns)(None)

  private def emptyBoardArray: BoardArray = Array.fill(boardRows)(emptyRow)

  private def figures(color: Color): Array[Piece] = Array(Rook(color), Knight(color), Bishop(color), Queen(color), King(color), Bishop(color), Knight(color), Rook(color))

  private def pawns(color: Color): Array[Piece] = Array.fill(boardColumns)(Pawn(color))

  private val whites = figures(White).zipWithIndex.map({ case (p, i) => PieceOnBoard(p, Point(i, 7)) }).toSeq ++
    pawns(White).zipWithIndex.map({ case (p, i) => PieceOnBoard(p, Point(i, 6)) })

  private val blacks = figures(Black).zipWithIndex.map({ case (p, i) => PieceOnBoard(p, Point(i, 0)) }).toSeq ++
    pawns(Black).zipWithIndex.map({ case (p, i) => PieceOnBoard(p, Point(i, 1)) })

  private val initialBoardArray: BoardArray =
    Array(
      figures(Black).map(Some.apply),
      pawns(Black).map(Some.apply),
      emptyRow,
      emptyRow,
      emptyRow,
      emptyRow,
      pawns(White).map(Some.apply),
      figures(White).map(Some.apply)
    )


  def emptyBoard: Board = new Board(emptyBoardArray, Nil, Nil)

  def initialBoard: Board = new Board(initialBoardArray, whites, blacks)

  override def pointString(point: Point): String = s"${(point.x + 97).asInstanceOf[Char]}${boardRows - point.y}"

  override def moveString(move: Move): String = s"${pointString(move.from)}${pointString(move.to)}"
}
