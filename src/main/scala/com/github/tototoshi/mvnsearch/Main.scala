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


class Main extends xsbti.AppMain {

  def run(config: xsbti.AppConfiguration): Exit =
    new Exit(Main.run(config.arguments))

}

class Exit(val code: Int) extends xsbti.Exit

object Main extends ResponseParser with Using with StringUtil {

  case class Config(searchWord: List[String])

  val parser = new scopt.immutable.OptionParser[Config]("mvnsearch", "0.2.1") { def options = Seq(
    arglist("<word>", "search word") { (w: String, c: Config) => c.copy(searchWord = c.searchWord ++ List(w)) }
  ) }

  def search(searchWord: List[String]): Set[Dependency] = {
    val url = "http://repository.sonatype.org/service/local/data_index?q=" + URLEncoder.encode(searchWord.mkString(" "), "utf-8")
    using(Source.fromURL(url)) { src =>
      val responseAsString = src.getLines.mkString("\n")
      parseResponse(responseAsString).toSet
    }
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
    search(config.searchWord).foreach(println)
  }


}

