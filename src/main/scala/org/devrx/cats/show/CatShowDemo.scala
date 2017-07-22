package org.devrx.cats.show

import cats.syntax.show._
import org.devrx.cats.Cat
import org.devrx.cats.instances.cat.showForCat

object CatShowDemo {
  def main(args: Array[String]): Unit = {
    val henry = Cat("Henry", 3, "Ginger")
    println(henry.show)
  }
}
