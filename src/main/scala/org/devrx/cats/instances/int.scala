package org.devrx.cats.instances

import org.devrx.cats.Printable

object int {
  implicit val intPrintable = new Printable[Int] {
    override def format(a: Int): String = a.toString
  }
}
