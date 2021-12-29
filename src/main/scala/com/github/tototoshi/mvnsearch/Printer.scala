package com.github.tototoshi.mvnsearch

object Printer {

  def printSbt(dependency: Dependency): String = (dependency.scalaArtifact, dependency.scalaVersion) match {
    case (Some(sa), Some(sv)) =>
      s""""${dependency.groupId}" %% "$sa" % "${dependency.version}"\t(for Scala $sv)"""
    case _ =>
      s""""${dependency.groupId}" % "${dependency.artifactId}" % "${dependency.version}""""
  }

  def print(dependencies: Seq[Dependency]): String = {
    dependencies.map(printSbt).mkString("\n")
  }

}
