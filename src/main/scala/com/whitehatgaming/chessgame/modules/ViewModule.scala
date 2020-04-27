package com.whitehatgaming.chessgame.modules

import com.whitehatgaming.chessgame.views.{GameView, SimpleTextGameView}

trait ViewModule {
  require: BoardModule =>
  lazy val gameView: GameView = new SimpleTextGameView(boardRules)
}
