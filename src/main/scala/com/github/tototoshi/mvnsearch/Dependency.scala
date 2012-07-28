package com.github.tototoshi.mvnsearch

case class Dependency(groupId: String, artifactId: String, version: String) extends StringUtil {
  override def toString = """%s %% %s %% %s""".format(quote(groupId), quote(artifactId), quote(version))
}
