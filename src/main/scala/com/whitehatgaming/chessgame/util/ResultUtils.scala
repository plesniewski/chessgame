package com.whitehatgaming.chessgame.util

import com.whitehatgaming.chessgame.{GameError, Result}

object ResultUtils {


  def validate(value:Boolean, err:GameError):Result[Unit] = {
    if (value) Right(()) else Left(err)
  }

  implicit class ResultExtensions[A](val underlying:Option[A]) extends AnyVal {
    def toResult(e: => Throwable):Result[A] = underlying.map(Right(_)).getOrElse(Left(e))
  }

}
