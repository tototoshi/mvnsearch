package com.github.tototoshi.mvnsearch

import io.circe._
import io.circe.generic.semiauto._
import io.circe.Decoder._

object MavenSearchResponse {

  case class Doc(g: String, a: String, latestVersion: String)

  case class Response(docs: List[Doc])

  case class Body(response: Response)

  implicit val docDecoder: Decoder[Doc] = deriveDecoder[Doc]
  implicit val responseDecoder: Decoder[Response] = deriveDecoder[Response]
  implicit val bodyDecoder: Decoder[Body] = deriveDecoder[Body]

}

