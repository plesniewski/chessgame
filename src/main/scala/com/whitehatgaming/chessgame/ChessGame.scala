package com.whitehatgaming.chessgame

import com.whitehatgaming.{UserInput, UserInputFile}
import com.whitehatgaming.chessgame.modules.{BoardModule, GameModule, MovesModule, ViewModule}


class GameApp extends GameModule with MovesModule with BoardModule with ViewModule

object ChessGame {

  type RunnableApp = GameApp with GameModule

  def main(args: Array[String]): Unit = {

    if (args.nonEmpty) {
      val input = new UserInputFile(args(0))
      run(
        new GameApp(), input
      )
    }
  }


  def run(app: RunnableApp, input: UserInput): Unit = {
    app.game.startGame(input, errorHandler)
  }

  private def errorHandler(e: Throwable): Unit = {
    e match {
      case error: GameError => println(error.message)
      case t: Throwable =>
        println("oopsie !", t)
    }
    System.exit(0)
  }

}
