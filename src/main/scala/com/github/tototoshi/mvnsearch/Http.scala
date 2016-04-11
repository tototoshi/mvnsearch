package com.github.tototoshi.mvnsearch

import java.net.URLEncoder

import scala.io.Source

object Http extends Using {

  def get(url: String, params: Map[String, String]): String = {
    using(Source.fromURL(url + buildQuery(params))) { src => src.mkString }
  }

  private def buildQuery(params: Map[String, String]): String =
    params.map { case (k, v) => e(k) + "=" + e(v) }.mkString("?", "&", "")

  private def e(s: String): String = URLEncoder.encode(s, "UTF-8")

}
