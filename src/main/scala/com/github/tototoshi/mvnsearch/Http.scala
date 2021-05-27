package com.github.tototoshi.mvnsearch

import java.net.URLEncoder
import scala.io.Source
import scala.util.Using

object Http {

  def get(url: String, params: Map[String, Any]): String = {
    Using.resource(Source.fromURL(url + buildQuery(params))) { src => src.mkString }
  }

  private def buildQuery(params: Map[String, Any]): String =
    params.map { case (k, v) => e(k) + "=" + e(v.toString) }.mkString("?", "&", "")

  private def e(s: String): String = URLEncoder.encode(s, "UTF-8")

}
