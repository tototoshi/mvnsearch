lazy val mvnsearch = project.in(file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "mvnsearch",
    organization := "com.github.tototoshi",
    version := "0.4.1-SNAPSHOT",
    scalaVersion := "2.13.7",
    scalacOptions ++= Seq("-feature"),
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "4.0.1",
      "org.json4s" %% "json4s-jackson" % "4.0.3",
      "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    ),
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
