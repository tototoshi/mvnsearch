lazy val mvnsearch = project
  .in(file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "mvnsearch",
    organization := "com.github.tototoshi",
    version := "0.5.2-SNAPSHOT",
    scalaVersion := "2.13.8",
    scalacOptions ++= Seq("-feature"),
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "4.0.1",
      "org.typelevel" %% "log4cats-slf4j" % "2.2.0",
      "ch.qos.logback" % "logback-classic" % "1.2.10",
      "io.circe" %% "circe-core" % "0.14.1",
      "io.circe" %% "circe-parser" % "0.14.1",
      "io.circe" %% "circe-generic" % "0.14.1",
      "org.typelevel" %% "cats-effect" % "3.3.6",
      "org.http4s" %% "http4s-circe" % "1.0.0-M31",
      "org.http4s" %% "http4s-dsl" % "1.0.0-M31",
      "org.http4s" %% "http4s-blaze-client" % "1.0.0-M31",
      "org.scalatest" %% "scalatest" % "3.2.11" % "test"
    ),
    run / fork := true,
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "com.github.tototoshi.mvnsearch.buildinfo",
    publishMavenStyle := true,
    publishTo := _publishTo(version.value),
    Test / publishArtifact := false,
    pomExtra := _pomExtra
  )

def _publishTo(v: String) = {
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

val _pomExtra =
  <url>http://github.com/tototoshi/mvnsearch</url>
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:tototoshi/mvnsearch.git</url>
    <connection>scm:git:git@github.com:tototoshi/mvnsearch.git</connection>
  </scm>
  <developers>
    <developer>
      <id>tototoshi</id>
      <name>Toshiyuki Takahashi</name>
      <url>http://tototoshi.github.com</url>
    </developer>
  </developers>
