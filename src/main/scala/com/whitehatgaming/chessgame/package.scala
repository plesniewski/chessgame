package com.whitehatgaming

import scala.util.control.NoStackTrace

package object chessgame {


case class GameError(message:String) extends Throwable with NoStackTrace

type GameResult[+T] = Either[GameError, T]


}
