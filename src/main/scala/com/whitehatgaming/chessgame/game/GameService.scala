package com.whitehatgaming.chessgame.game

import java.io.IOException

import com.whitehatgaming.UserInput
import com.whitehatgaming.chessgame.board.{Board, BoardRules}
import com.whitehatgaming.chessgame.domain.Colors.White
import com.whitehatgaming.chessgame.domain.{Colors, Move, PieceOnBoard}
import com.whitehatgaming.chessgame.moves.{BoardUpdated, GameError, MoveError, MovesService}
import com.whitehatgaming.chessgame.views.GameView


trait GameService {
  def startGame(input: UserInput, onError: GameError => Unit): Unit
}


class GameServiceImpl(
                       movesService: MovesService,
                       gameView: GameView,
                       boardRules: BoardRules
                     ) extends GameService {

  private def initialBoard: Board = boardRules.initialBoard

  private def getValidNextMoveData(input: UserInput): Option[Array[Int]] = {
    try {
      Option(input.nextMove())
    } catch {
      case e: IOException =>
        println(s"invalid input", e)
        None
    }
  }

  private def getValidMove(array: Array[Int]): Option[Move] = {
    Some(Move(array)).filter(boardRules.isMoveValid)
  }

  def startGame(input: UserInput, onGameError: GameError => Unit): Unit = {
    var inputData = getValidNextMoveData(input)
    var player = White

    var board = initialBoard
    var currentCheck: Option[PieceOnBoard] = None

    while (inputData.nonEmpty) {

      inputData.flatMap(getValidMove).map(move => {

        movesService.processMove(board, move, player, currentCheck.isDefined) match {
          case Right(BoardUpdated(updated, checksBy)) =>
            board = updated
            currentCheck = checksBy
            player = Colors.opponet(player)
            gameView.drawSuccess(board, checksBy, move, currentPlayer = player)
            Console.in.read()
            inputData = getValidNextMoveData(input)
          case Left(error) =>
            error match {
              case e: GameError => onGameError(e)
              case e: MoveError =>
                gameView.drawFailure(board, currentCheck, move, currentPlayer = player, e)
            }
            inputData = None
        }
      }).getOrElse({
        println("Invalid input: Move our of board")
      })
    }
  }
}
