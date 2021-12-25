package com.github.tototoshi.mvnsearch

import cats.effect.IO
import org.slf4j.LoggerFactory
import org.slf4j.{ Logger => Slf4jLogger }

class Logger(logger: Slf4jLogger) {

  def debug(message: String): IO[Unit] = {
    IO.blocking(logger.debug(message))
  }

  def info(message: String): IO[Unit] = {
    IO.blocking(logger.info(message))
  }

  def warn(message: String): IO[Unit] = {
    IO.blocking(logger.warn(message))
  }

  def error(message: String): IO[Unit] = {
    IO.blocking(logger.error(message))
  }

}

object Logger {

  def apply(name: Class[_]): IO[Logger] = {
    IO.delay(new Logger(LoggerFactory.getLogger(name)))
  }

}
