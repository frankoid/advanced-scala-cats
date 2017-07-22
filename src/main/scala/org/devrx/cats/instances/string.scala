package org.devrx.cats.instances

import org.devrx.cats.Printable

object string {
  implicit val stringPrintable = new Printable[String] {
    override def format(a: String): String = a
  }
}
