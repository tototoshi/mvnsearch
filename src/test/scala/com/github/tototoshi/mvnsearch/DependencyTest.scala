package com.github.tototoshi.mvnsearch

import org.scalatest.funsuite.AnyFunSuite

class DependencyTest extends AnyFunSuite {

  test("Parse artifactId (Scala 2.13)") {
    val g = "org.typelevel"
    val a = "cats-effect_2.13"
    val latestVersion = "3.3.1"
    val dep = Dependency(g, a, latestVersion)
    assert(dep.scalaVersion === Some("2.13"))
  }

  test("Parse artifactId (Scala 3)") {
    val g = "org.typelevel"
    val a = "cats-effect_3"
    val latestVersion = "3.3.1"
    val dep = Dependency(g, a, latestVersion)
    assert(dep.scalaVersion === Some("3"))
  }

  test("Parse artifactId (Unstable releases)") {
    val g = "org.tpolecat"
    val a = "doobie-core_3.0.0-RC2"
    val latestVersion = "1.0.0-M2"
    val dep = Dependency(g, a, latestVersion)
    assert(dep.scalaVersion === Some("3.0.0-RC2"))
  }

}
