import sbt._
import sbt.Keys._

object MvnsearchBuild extends Build {

  lazy val mvnsearch = Project(
    id = "mvnsearch",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "mvnsearch",
      organization := "com.github.tototoshi",
      version := "0.2.0",
      scalaVersion := "2.10.0",
      resolvers += "sonatype-public" at "https://oss.sonatype.org/content/groups/public",
      resolvers <+= sbtResolver,
      scalacOptions ++= Seq("-feature"),
      libraryDependencies ++= Seq(
        "com.github.scopt" %% "scopt" % "2.1.0",
        "org.scalatest" %% "scalatest" % "1.9.1" % "test",
        "org.scala-sbt" % "command" % "0.12.2"
      )
    )
  )

}
