package com.whitehatgaming.chessgame.game

import com.whitehatgaming.UserInput
import com.whitehatgaming.chessgame.board.{Board, BoardRules}
import com.whitehatgaming.chessgame.domain.{Colors, Move}
import com.whitehatgaming.chessgame.domain.Colors.White
import com.whitehatgaming.chessgame.moves.{MoveResult, MovesService}
import com.whitehatgaming.chessgame.views.GameView
import scala.sys.process._


trait GameService {
  def startGame(input:UserInput, onError: Throwable => Unit): Unit
}

class GameServiceImpl(
                   movesService: MovesService,
                   gameView: GameView,
                   boardRules: BoardRules
                 ) extends GameService {


  private def clearScreen: Int = {
    "clear".!
  }

  private def initialBoard: Board = boardRules.initialBoard


  def startGame(input:UserInput, onError: Throwable => Unit): Unit = {
    var inputData = input.nextMove()
    var player = White
    var playerInCheck = false

    var board = initialBoard

    while (inputData != null) {
      val move = Move(inputData)
      movesService.processMove(board, move, player, playerInCheck) match {
        case Right(MoveResult(updated, inCheckBy, checksBy)) =>
          board = updated
          playerInCheck = inCheckBy.isDefined || checksBy.isDefined
          player = if (inCheckBy.isDefined) player else Colors.opponet(player)
          clearScreen
          gameView.drawBoard(board, checksBy.orElse(inCheckBy))
          Console.in.read()
          inputData = input.nextMove()
        case Left(error) =>
          onError(error)
      }
    }
  }
}
