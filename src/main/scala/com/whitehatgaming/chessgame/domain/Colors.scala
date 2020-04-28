package com.whitehatgaming.chessgame.domain

object Colors extends Enumeration {
  type Color = Value
  val White, Black = Value

  def opponet(color: Color): Color = this (1 - color.id)
}
