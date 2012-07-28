package com.github.tototoshi.mvnsearch

import org.specs2.mutable._

class SearchResultFilterSpec extends Specification with SearchResultFilter {

  "SearchResultFilter" should {

    "filter with scalaVersion" in {
      val d1 = Dependency("foo", "foo_2.9.1", "1.0")
      val d2 = Dependency("foo", "foo_2.9.1", "1.1")
      val d3 = Dependency("foo", "foo_2.9.2", "1.0")
      val dependencies = Seq(d1, d2, d3)
      scalaVersionFilter(dependencies, Some("2.9.1")) === Seq(d1, d2)
      scalaVersionFilter(dependencies, None) === Seq(d1, d2, d3)
    }

    "filter with version" in {
      val d1 = Dependency("foo", "foo_2.9.1", "1.0")
      val d2 = Dependency("foo", "foo_2.9.1", "1.1")
      val d3 = Dependency("foo", "foo_2.9.2", "1.0")
      val dependencies = Seq(d1, d2, d3)
      versionFilter(dependencies, Some("1.1")) === Seq(d2)
      versionFilter(dependencies, None) === Seq(d1, d2, d3)
    }

  }

}

