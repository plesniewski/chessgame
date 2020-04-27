package com.whitehatgaming.chessgame

package object moves {

  trait MoveFailure {
    def message: String
  }

  type MoveResult[T] = Either[MoveFailure, T]

  def validate(value: Boolean, err: => MoveFailure): MoveResult[Unit] = {
    if (value) Right(()) else Left(err)
  }

  implicit class ResultExtensions[A](val underlying: Option[A]) extends AnyVal {
    def toResult(e: => MoveFailure): MoveResult[A] = underlying.map(Right(_)).getOrElse(Left(e))
  }

}
