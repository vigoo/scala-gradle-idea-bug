package io.github.vigoo.example

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

@compileTimeOnly("enable macro paradise to expand macro annotations")
class test extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro MacroImpl.impl
}

private object MacroImpl {
  def impl(c: blackbox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._

    annottees.map(_.tree).head match {
      case q"object $name { ..$body }" =>
        val enriched = q"""object $name {
          def name: String = "world"
          ..$body
        }
        """

        c.Expr[Any](enriched)
      case _ =>
        throw new Exception("Cannot apply macro to $annottees")
    }
  }
}
