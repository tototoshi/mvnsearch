package com.github.tototoshi.mvnsearch

import cats.effect.IO

object MavenSearch {

  def search(config: Config): IO[Seq[Dependency]] = {
    val params = Map("q" -> config.searchWord.mkString(" "), "rows" -> config.rows)
    for {
      logger <- Logger(MavenSearch.getClass)
      response <- Http.get("https://search.maven.org/solrsearch/select", params)
      _ <- logger.debug(response)
      response <- parseResponse(response)
    } yield response
  }

  private def parseResponse(json: String): IO[Seq[Dependency]] = {
    import io.circe.parser._
    def toDep(body: MavenSearchResponse.Body): Seq[Dependency] =
      body
        .response
        .docs
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
