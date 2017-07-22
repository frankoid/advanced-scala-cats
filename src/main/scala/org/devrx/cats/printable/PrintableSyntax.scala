package org.devrx.cats.printable

object PrintableSyntax {
  implicit class PrintOps[A](a: A) {
    def format(implicit printable: Printable[A]) = Printable.format(a)
    def print(implicit printable: Printable[A]) = Printable.print(a)
  }
}
