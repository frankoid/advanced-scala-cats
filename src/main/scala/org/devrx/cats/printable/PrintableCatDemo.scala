package org.devrx.cats.printable

import org.devrx.cats.Cat

object PrintableCatDemo {
  def main(args: Array[String]): Unit = {
    import PrintableSyntax._
    import org.devrx.cats.instances.cat.printableForCat

    val henry = Cat("Henry", 3, "Ginger")
    henry.print
  }
}
