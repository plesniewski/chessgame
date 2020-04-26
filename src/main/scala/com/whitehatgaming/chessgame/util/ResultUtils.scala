package com.whitehatgaming.chessgame.util

import com.whitehatgaming.chessgame.{GameError, Result}

object ResultUtils {


  def validate(value:Boolean, err:GameError):Result[Unit] = {
    if (value) Right(()) else Left(err)
  }

}
