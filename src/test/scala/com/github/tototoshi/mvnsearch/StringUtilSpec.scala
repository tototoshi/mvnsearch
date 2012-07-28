package com.github.tototoshi.mvnsearch

import org.specs2.mutable._

class StringUtilSpec extends Specification with StringUtil {

  "StringUtil" should {

    "quote string" in {
      quote("a") === "\"a\""
    }

  }

}
