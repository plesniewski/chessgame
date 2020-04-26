package com.whitehatgaming

import scala.util.control.NoStackTrace

package object chessgame {


trait GameError extends Throwable with NoStackTrace {
  def message:String
}

type Result[T] = Either[Throwable, T]

}
