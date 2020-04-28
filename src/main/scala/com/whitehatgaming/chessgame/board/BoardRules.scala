package com.whitehatgaming.chessgame.board

import com.whitehatgaming.chessgame.domain.{Move, Point}

trait BoardRules {

  def boardRows: Int

  def boardColumns: Int

  def isMoveValid(p: Move): Boolean

  def emptyBoard: Board

  def initialBoard: Board

  def pointString(point: Point): String

  def moveString(move: Move): String
}

