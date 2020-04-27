package com.whitehatgaming.chessgame.modules

import com.whitehatgaming.chessgame.game.{GameService, GameServiceImpl}

trait GameModule {
  requires: MovesModule with ViewModule with BoardModule =>

  lazy val game:GameService = new GameServiceImpl(movesService, gameView, boardRules)
}
