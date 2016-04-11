package com.github.tototoshi.mvnsearch

import java.net.URLEncoder

import scala.io.Source

object Http extends Using {

  def get(url: String, params: Map[String, Any]): String = {
    using(Source.fromURL(url + buildQuery(params))) { src => src.mkString }
  }

  private def buildQuery(params: Map[String, Any]): String =
    params.map { case (k, v) => e(k) + "=" + e(v.toString) }.mkString("?", "&", "")

  private def e(s: String): String = URLEncoder.encode(s, "UTF-8")

}
