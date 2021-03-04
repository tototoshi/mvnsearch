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

import java.net.URLEncoder
import scala.io.Source
import org.json4s._
import org.json4s.jackson.JsonMethods._

object Main extends Using {

  case class Config(searchWord: List[String], rows: Int)

  val parser = new scopt.OptionParser[Config]("mvnsearch") {
    opt[Int]("rows").action { (rows, c) => c.copy(rows = rows) }
    arg[String]("<word>").action { (w: String, c: Config) => c.copy(searchWord = c.searchWord ++ List(w)) }.unbounded()
  }

  def parseResponse(json: String): List[Dependency] = {
    val ast = parse(json)
    for {
      JObject(doc) <- ast \ "response" \ "docs"
      docMap = doc.toMap
      JString(g) <- docMap.get("g")
      JString(a) <- docMap.get("a")
      JString(v) <- docMap.get("latestVersion")
    } yield Dependency(g, a, v)
  }

  def search(config: Config): Seq[Dependency] = {
    val params = Map("q" -> config.searchWord.mkString(" "), "rows" -> config.rows)
    val response = Http.get("http://search.maven.org/solrsearch/select", params)
    parseResponse(response)
  }

  def main(args: Array[String]): Unit = {
    parser.parse(args, Config(Nil, 20)).foreach { config =>
      val out = Printer.print(search(config))
      println(out)
    }
  }

}
