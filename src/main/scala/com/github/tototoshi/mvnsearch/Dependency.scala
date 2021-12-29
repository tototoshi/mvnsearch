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

case class Dependency(groupId: String, artifactId: String, version: String) {

  private val versionRegex = """(.*?)_(.*?)$""".r

  val (scalaArtifact, scalaVersion): (Option[String], Option[String]) = artifactId match {
    case versionRegex(a, v) => (Some(a), Some(v))
    case _ => (None, None)
  }

  val isScala: Boolean = scalaVersion.nonEmpty

}
