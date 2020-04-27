package com.whitehatgaming.chessgame.game

import com.whitehatgaming.UserInput
import com.whitehatgaming.chessgame.board.{Board, BoardRules}
import com.whitehatgaming.chessgame.domain.Colors.White
import com.whitehatgaming.chessgame.domain.{Colors, Move, PieceOnBoard}
import com.whitehatgaming.chessgame.moves.{BoardUpdated, GameError, MovesService}
import com.whitehatgaming.chessgame.views.GameView

import scala.sys.process._


trait GameService {
  def startGame(input: UserInput, onError: GameError => Unit): Unit
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


  def startGame(input: UserInput, onGameError: GameError => Unit): Unit = {
    var inputData = input.nextMove()
    var player = White
    var playerInCheck = false

    var board = initialBoard
    var currentCheck: Option[PieceOnBoard] = None

    while (inputData != null) {
      val move = Move(inputData)
      movesService.processMove(board, move, player, currentCheck.isDefined) match {
        case Right(BoardUpdated(updated, checksBy)) =>
          board = updated
          currentCheck = checksBy
          player = Colors.opponet(player)
          clearScreen
          gameView.drawBoard(board, checksBy, move, currentPlayer = player)
          Console.in.read()
          inputData = input.nextMove()
        case Left(error) =>
          error match {
            case e: GameError => onGameError(e)
            case _ =>
              clearScreen
              gameView.drawBoard(board, currentCheck, move, currentPlayer = player, markMove = true)
              println(error.message)
          }
          inputData = null
      }
    }
  }
}
