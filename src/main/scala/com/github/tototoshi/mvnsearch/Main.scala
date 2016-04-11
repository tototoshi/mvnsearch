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

class Main extends xsbti.AppMain {

  def run(config: xsbti.AppConfiguration): Exit =
    new Exit(Main.run(config.arguments))

}

class Exit(val code: Int) extends xsbti.Exit

object Main extends Using with StringUtil {

  case class Config(searchWord: List[String])

  val parser = new scopt.OptionParser[Config]("mvnsearch") {
    arg[String]("<word>") unbounded() action { (w: String, c: Config) => c.copy(searchWord = c.searchWord ++ List(w)) }
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

  def search(searchWord: List[String]): Seq[Dependency] = {
    val params = Map("q" -> searchWord.mkString(" "))
    val response = Http.get("http://search.maven.org/solrsearch/select", params)
    parseResponse(response)
  }

  def run(args: Array[String]): Int = {
    try {
      main(args)
      0
    } catch {
      case e: IllegalArgumentException => {
        println("search word required.")
        1
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val config = parser.parse(args, Config(Nil)).getOrElse(throw new IllegalArgumentException)
    val out = Printer.print(search(config.searchWord))
    println(out)
  }

}

