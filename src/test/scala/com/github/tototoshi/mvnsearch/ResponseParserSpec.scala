/*
* Copyright 2013 Toshiyuki Takahashi
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.github.tototoshi.mvnsearch

import org.scalatest.FunSpec
import org.scalatest.matchers._
import scala.io.Source

class ResponseParserSpec extends FunSpec with ShouldMatchers with ResponseParser {

  def using[A, R <: { def close() }](r : R)(f : R => A) : A =
    try { f(r) } finally { r.close() }

  describe("ResponseParser") {

    it("should parse response xml") {
      val testXml = "src/test/resources/testdata/response.xml"
      val data = using (Source.fromFile(testXml)) { _.mkString }
      val expected = List(
        Dependency("org.apache.maven.surefire",
                   "maven-surefire-common",
                   "2.7-SONATYPE-1033415"),
        Dependency("org.apache.maven.plugins",
                   "maven-surefire-report-plugin",
                   "2.7-SONATYPE-1033415")
      )
      parseResponse(data) should be (expected)
    }

  }
}

