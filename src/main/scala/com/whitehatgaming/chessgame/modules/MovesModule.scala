package com.whitehatgaming.chessgame.modules

import com.whitehatgaming.chessgame.moves.{DefaultMovesService, MovesService, MovesValidations, PiecesMovesValidations}

trait MovesModule {

  val movesValidations: MovesValidations = PiecesMovesValidations
  val movesService: MovesService = new DefaultMovesService(movesValidations)
}
