package com.github.tototoshi.mvnsearch

trait StringUtil {

  def quote(s: String): String = s.mkString("\"", "", "\"")

}

