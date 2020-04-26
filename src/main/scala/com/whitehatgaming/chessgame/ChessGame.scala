package com.whitehatgaming.chessgame

import com.whitehatgaming.{UserInput, UserInputFile}
import com.whitehatgaming.chessgame.board.BoardRules
import com.whitehatgaming.chessgame.domain.{Colors, Move}
import com.whitehatgaming.chessgame.game.{MoveResult, MovesService, PiecesMovesValidations}
import com.whitehatgaming.chessgame.domain.Colors._

object ChessGame {

  def main(args: Array[String]): Unit = {

    if (args.nonEmpty) {
      val input = new UserInputFile(args(0))
      input.nextMove()
    }
  }

  def beginGame(input:UserInputFile): Unit = {

    val boardRules: BoardRules = new BoardRules()
    val moves = new MovesService(new PiecesMovesValidations)
    var board = boardRules.initialBoard

    var inputData = input.nextMove()
    var player = White
    var playerInCheck = false

    while (inputData != null) {
      val move = Move(inputData)
      moves.processMove(board, move, player, playerInCheck) match {
        case Right(MoveResult(updated, inCheckBy, checksBy)) =>
          board = updated
          playerInCheck = inCheckBy.isDefined || checksBy.isDefined
          player = if (inCheckBy.isDefined) player else Colors.opponet(player)
          inputData = input.nextMove()
        case Left(error)=>
          error match {
            case error1: GameError => println(error1.message)
            case _ => println("oopsie !", error)
          }
      }


    }

  }

}
