lazy val mvnsearch = project.in(file("."))
  .settings(
    name := "mvnsearch",
    organization := "com.github.tototoshi",
    version := "0.4.0-SNAPSHOT",
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq("-feature"),
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "3.4.0",
      "org.scalatest" %% "scalatest" % "2.2.5" % "test",
      "org.json4s" %% "json4s-jackson" % "3.3.0"
    ),
    publishMavenStyle := true,
    publishTo := _publishTo(version.value),
    publishArtifact in Test := false,
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
