package com.whitehatgaming.chessgame.domain

object MoveTypes extends Enumeration {
  type MoveType = Value
  val Diagonal, Vertical,Horizontal, Special = Value
}
