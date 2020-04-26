package com.whitehatgaming.chessgame.game

import com.whitehatgaming.chessgame.board.Board
import com.whitehatgaming.chessgame.domain.Colors.Color


sealed trait GameStatus
case class InProgress(board: Board) extends GameStatus
case class Check(color:Color, board:Board) extends GameStatus

class GameService(movesService: MovesService) {




}
