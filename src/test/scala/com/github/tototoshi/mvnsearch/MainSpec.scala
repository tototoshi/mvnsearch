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

class MainSpec extends FunSpec with ShouldMatchers {
  describe("Main") {

    it("should search libraries from maven repository") {
      val result = Main.search(List("scopt"))
      val scoptDep = Dependency("com.github.scopt", "scopt_2.9.1", "2.1.0")
      result should contain (scoptDep)
    }

  }
}

