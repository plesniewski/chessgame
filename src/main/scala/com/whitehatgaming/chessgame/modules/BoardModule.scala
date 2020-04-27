package com.whitehatgaming.chessgame.modules

import com.whitehatgaming.chessgame.board.{BoardRules, ClassicBoardRules}

trait BoardModule {
  val boardRules: BoardRules = ClassicBoardRules
}
