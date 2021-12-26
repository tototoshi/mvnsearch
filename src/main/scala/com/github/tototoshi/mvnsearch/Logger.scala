package com.github.tototoshi.mvnsearch

import cats.effect.Sync
import org.slf4j.LoggerFactory

trait Logger[F[_]] {
  def debug(message: String): F[Unit]
  def info(message: String): F[Unit]
  def warn(message: String): F[Unit]
  def error(message: String): F[Unit]
}

object Logger {

  def apply[F[_]: Sync](name: Class[_]): Logger[F] = impl(name.getCanonicalName)

  private def impl[F[_]: Sync](name: String): Logger[F] = new Logger[F] {
    private val logger = LoggerFactory.getLogger(name)
    override def debug(message: String): F[Unit] = Sync[F].blocking(logger.debug(message))
    override def info(message: String): F[Unit] = Sync[F].blocking(logger.info(message))
    override def warn(message: String): F[Unit] = Sync[F].blocking(logger.warn(message))
    override def error(message: String): F[Unit] = Sync[F].blocking(logger.error(message))
  }

}
