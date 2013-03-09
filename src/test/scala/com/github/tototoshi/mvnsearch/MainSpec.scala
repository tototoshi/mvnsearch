package com.github.tototoshi.mvnsearch

import org.scalatest.FunSpec
import org.scalatest.matchers._

class MainSpec extends FunSpec with ShouldMatchers {
  describe("Main") {

    it("should search libraries from maven repository") {
      val result = Main.search(List("scopt"))
      val scoptDep = Dependency("com.github.scopt", "scopt_2.9.1", "2.1.0")
      result should contain (scoptDep)
    }

  }
}

