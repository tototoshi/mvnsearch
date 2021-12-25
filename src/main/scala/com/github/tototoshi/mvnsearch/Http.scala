package com.github.tototoshi.mvnsearch

import cats.effect._

import java.net.URLEncoder
import scala.io.{BufferedSource, Source}

object Http {

  def get(url: String, params: Map[String, Any]): IO[String] = {

    def acquire: IO[BufferedSource] = IO.blocking(Source.fromURL(url + buildQuery(params)))

    def close(src: BufferedSource): IO[Unit] = IO.blocking(src.close())

    Resource.make(acquire)(close).use(src => IO(src.mkString))
  }

  private def buildQuery(params: Map[String, Any]): String =
    params.map { case (k, v) => e(k) + "=" + e(v.toString) }.mkString("?", "&", "")

  private def e(s: String): String = URLEncoder.encode(s, "UTF-8")

}
