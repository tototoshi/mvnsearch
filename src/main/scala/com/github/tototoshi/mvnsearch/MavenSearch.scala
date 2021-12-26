package com.github.tototoshi.mvnsearch

import cats.effect.IO
import org.http4s.Request
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.implicits._

object MavenSearch {

  def search(config: Config): IO[Seq[Dependency]] = {
    import MavenSearchResponse._
    import org.http4s.circe.CirceEntityDecoder._

    val params = Map("q" -> config.searchWord.mkString(" "), "rows" -> config.rows.toString)
    val request = Request[IO](uri = uri"https://search.maven.org/solrsearch/select".withQueryParams(params))

    for {
      logger <- Logger(MavenSearch.getClass)
      response <- BlazeClientBuilder[IO].resource
        .use(client => client.expect[MavenSearchResponse.Body](request))
      _ <- logger.debug(response.toString)
      response <- parseResponse(response.toString)
    } yield response
  }

  private def parseResponse(json: String): IO[Seq[Dependency]] = {
    import io.circe.parser._
    def toDep(body: MavenSearchResponse.Body): Seq[Dependency] =
      body.response.docs
        .map(item => Dependency(item.g, item.a, item.latestVersion))

    for {
      logger <- Logger(MavenSearch.getClass)
      response <- IO(decode[MavenSearchResponse.Body](json))
      items <-
        response match {
          case Right(r) => IO(toDep(r))
          case Left(e) => IO.blocking(logger.info(e.toString)).map(_ => Seq.empty)
        }
    } yield items
  }

}
