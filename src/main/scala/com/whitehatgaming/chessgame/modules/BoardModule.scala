package com.whitehatgaming.chessgame.modules

import com.whitehatgaming.chessgame.board.BoardRules

trait BoardModule {
    val boardRules:BoardRules = new BoardRules
}
