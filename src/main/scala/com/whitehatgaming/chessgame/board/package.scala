package com.whitehatgaming.chessgame

import com.whitehatgaming.chessgame.domain.Piece

package object board {
  type BoardArray = Array[Array[Option[Piece]]]
}
