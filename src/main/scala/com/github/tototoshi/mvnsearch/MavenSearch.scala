package com.github.tototoshi.mvnsearch

import cats.effect._
import cats.implicits._
import org.http4s.EntityDecoder
import org.http4s.Method.GET
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.circe._
import org.http4s.client.dsl.Http4sClientDsl
import org.http4s.implicits._
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

trait MavenSearch[F[_]] {
  def search(config: Config): F[Seq[Dependency]]
}

object MavenSearch {

  def apply[F[_]](implicit ev: MavenSearch[F]): MavenSearch[F] = ev

  implicit def impl[F[_]: Async]: MavenSearch[F] = new MavenSearch[F] {

    implicit def logger: Logger[F] = Slf4jLogger.getLogger[F]

    implicit def mavenSearchResponseDecoder: EntityDecoder[F, MavenSearchResponse.Body] = jsonOf

    def search(config: Config): F[Seq[Dependency]] = {
      val dsl = new Http4sClientDsl[F] {}
      import dsl._

      val request = GET(
        uri"https://search.maven.org/solrsearch/select" +?
          ("q" -> config.searchWord.mkString(" ")) +?
          ("rows" -> config.rows.toString)
      )

      for {
        response <- BlazeClientBuilder[F].resource
          .use(client => client.expect[MavenSearchResponse.Body](request))
        _ <- Logger[F].debug(response.toString)
      } yield toDeps(response)
    }

    private def toDeps(body: MavenSearchResponse.Body): Seq[Dependency] =
      body.response.docs
        .map(item => Dependency(item.g, item.a, item.latestVersion))

  }

}
