import sbt._
import sbt.Keys._

object MvnsearchBuild extends Build {

  lazy val mvnsearch = Project(
    id = "mvnsearch",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "mvnsearch",
      organization := "com.github.tototoshi",
      version := "0.1.2",
      scalaVersion := "2.9.1",
      resolvers += "sonatype-public" at "https://oss.sonatype.org/content/groups/public",
      resolvers <+= sbtResolver,
      libraryDependencies ++= Seq(
        "com.github.scopt" %% "scopt" % "2.1.0",
        "org.specs2" %% "specs2" % "1.11" % "test",
        "org.scala-sbt" %% "launcher-interface" % "0.11.3" % "provided"
      )
    )
  )

}
