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

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import com.github.tototoshi.mvnsearch.buildinfo.BuildInfo
import org.slf4j.LoggerFactory

object Main extends IOApp {

  private val logger = LoggerFactory.getLogger(getClass)

  private val parser = new scopt.OptionParser[Config]("mvnsearch") {
    head(BuildInfo.name, BuildInfo.version)
    version('v', "version").text("Print the version")
    help('h', "help").text("Print a help")
    opt[Int]("rows").action { (rows, c) => c.copy(rows = rows) }.text("Specify the number of rows")
    arg[String]("<word>")
      .action { (w: String, c: Config) => c.copy(searchWord = c.searchWord ++ List(w)) }
      .unbounded()
      .text("Specify search words")
  }

  private def program(config: Config): IO[Unit] =
    for {
      dependencies <- MavenSearch[IO].search(config)
      out = Printer.print(dependencies)
      _ <- IO.println(out)
    } yield ()

  override def run(args: List[String]): IO[ExitCode] = {
    parser.parse(args, Config(Nil, 20)) match {
      case Some(config) => program(config).map(_ => ExitCode.Success)
      case None => IO.pure(ExitCode.Error)
    }
  }

}
