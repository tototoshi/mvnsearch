package com.github.tototoshi.mvnsearch

import scala.language.reflectiveCalls

protected trait Using {

  def using[A, R <: { def close() }](r : R)(f : R => A) : A =
    try { f(r) } finally { r.close() }

}
