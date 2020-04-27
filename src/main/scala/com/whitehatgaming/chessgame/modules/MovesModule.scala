package com.whitehatgaming.chessgame.modules

import com.whitehatgaming.chessgame.moves.{MovesService, MovesServiceImpl, MovesValidations, PiecesMovesValidations}

trait MovesModule {

  val movesValidations: MovesValidations = PiecesMovesValidations
  val movesService:MovesService = new MovesServiceImpl(movesValidations)
}
