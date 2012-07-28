package com.github.tototoshi.mvnsearch

import scala.xml.XML

trait ResponseParser {

  def parseResponse(response: String): Seq[Dependency] = {
    (XML.loadString(response) \\ "artifact").map { x =>
      Dependency(x \\ "groupId" text, x \\ "artifactId" text, x \\ "version" text)
    }
  }

}
