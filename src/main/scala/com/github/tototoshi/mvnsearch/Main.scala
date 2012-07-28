package com.github.tototoshi.mvnsearch

import java.net.URLEncoder
import scala.io.Source


class Main extends xsbti.AppMain {

  def run(config: xsbti.AppConfiguration): Exit =
    new Exit(Main.main(config.arguments))

}

class Exit(val code: Int) extends xsbti.Exit

object Main extends ResponseParser with Using with StringUtil with SearchResultFilter {

  case class Config(searchWord: String, scalaVersion: Option[String], version: Option[String])

  val parser = new scopt.immutable.OptionParser[Config]("mvnsearch", "0.1.2") { def options = Seq(
    arg("<word>", "search word") { (w: String, c: Config) => c.copy(searchWord = w) },
    opt("s", "scala-version", "scala version") { (s: String, c: Config) => c.copy(scalaVersion = Some(s)) },
    opt("v", "version", "version") { (v: String, c: Config) => c.copy(version = Some(v)) }
  ) }

  def search(searchWord: String, scalaVersion: Option[String], version: Option[String]): Set[Dependency] = {
    val url = "http://repository.sonatype.org/service/local/data_index?q=" + URLEncoder.encode(searchWord, "utf-8")
    using(Source.fromURL(url)) { src =>
      val responseAsString = src.getLines.mkString("\n")
      val dependencies = parseResponse(responseAsString)
      val dependenciesFilteredByScalaVersion = scalaVersionFilter(dependencies, scalaVersion)
      val dependenciesFilteredByVersion = versionFilter(dependenciesFilteredByScalaVersion, version)
      dependenciesFilteredByVersion.toSet
    }
  }

  def main(args: Array[String]): Int = {
    parser.parse(args, Config("", None, None)) map { config =>
      search(config.searchWord, config.scalaVersion, config.version).foreach(println)
      0
    } getOrElse {
      println("search word required.")
      1
    }
  }


}

