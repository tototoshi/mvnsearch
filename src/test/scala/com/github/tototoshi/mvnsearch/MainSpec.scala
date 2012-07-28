package com.github.tototoshi.mvnsearch

import org.specs2.mutable._

class MainSpec extends Specification {

  "Main" should {

    "search" in {
      Main.search("scopt", Some("2.9.1"), Some("2.1.0")).head === Dependency("com.github.scopt", "scopt_2.9.1", "2.1.0")
    }

  }

}
