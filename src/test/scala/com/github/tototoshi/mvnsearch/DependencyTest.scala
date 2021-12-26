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

}
