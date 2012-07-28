package com.github.tototoshi.mvnsearch

trait SearchResultFilter {

  def scalaVersionFilter(dependencies: Seq[Dependency], scalaVersion: Option[String]): Seq[Dependency] = {
    dependencies.filter { d =>
      scalaVersion.map { v => d.artifactId.endsWith("_" + v) }.getOrElse(true)
    }
  }

  def versionFilter(dependencies: Seq[Dependency], version: Option[String]): Seq[Dependency] = {
    dependencies.filter { d =>
      version.map { v => d.version.startsWith(v) }.getOrElse(true)
    }
  }

}
