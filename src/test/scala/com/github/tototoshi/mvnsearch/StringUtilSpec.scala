package com.github.tototoshi.mvnsearch

import org.scalatest.FunSpec
import org.scalatest.matchers._

class StringUtilSpec extends FunSpec with ShouldMatchers with StringUtil {

  describe("StringUtil") {
    it("should quote string") {
      quote("a") should be ("\"a\"")
    }
  }
}
