package com.whitehatgaming

import scala.util.control.NoStackTrace

package object chessgame {


abstract class GameError(message:String) extends Throwable with NoStackTrace

type Result[+T] = Either[GameError, T]

}
