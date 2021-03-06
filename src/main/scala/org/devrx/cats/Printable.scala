package org.devrx.cats

trait Printable[A] { printableA =>
  def format(a: A): String

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      override def format(value: B) = printableA.format(func(value))
    }
}

object Printable {
  def format[A](a: A)(implicit printable: Printable[A]): String =
    printable.format(a)

  def print[A](a: A)(implicit printable: Printable[A]): Unit =
    println(format(a))
}
