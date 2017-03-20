package io.github.vigoo.example

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.language.experimental.macros

object Main extends App {
    println(s"Hello ${Test.name}")
}
