package com.whitehatgaming.chessgame.modules

import com.whitehatgaming.chessgame.views.{GameView, SimpleTextGameView}

trait ViewModule {
  val gameView:GameView = SimpleTextGameView
}
