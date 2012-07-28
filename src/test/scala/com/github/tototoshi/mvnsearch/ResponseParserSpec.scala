package com.github.tototoshi.mvnsearch

import org.specs2.mutable._
import scala.io.Source

class ResponseParserSpec extends Specification with ResponseParser {

  "ResponseParser" should {

    "parse response xml" in {
      val data = Source.fromFile("src/test/resources/testdata/response.xml").getLines.mkString
      val expected = List(
        Dependency("org.apache.maven.surefire", "maven-surefire-common", "2.7-SONATYPE-1033415"),
        Dependency("org.apache.maven.plugins", "maven-surefire-report-plugin", "2.7-SONATYPE-1033415")
      )
      parseResponse(data) === expected
    }

  }

}
