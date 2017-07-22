package org.devrx.cats.printable

import org.devrx.cats.Printable

object PrintableSyntax {
  implicit class PrintOps[A](a: A) {
    def format(implicit printable: Printable[A]) = Printable.format(a)
    def print(implicit printable: Printable[A]) = Printable.print(a)
  }
}
