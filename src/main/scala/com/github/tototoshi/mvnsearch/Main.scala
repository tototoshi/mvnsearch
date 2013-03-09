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

  val parser = new scopt.immutable.OptionParser[Config]("mvnsearch", "0.1.2") { def options = Seq(
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

