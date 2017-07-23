package org.devrx.cats.superadder

import cats.Monoid

object SuperAdder {
  def add[A](items: List[A])(implicit m: Monoid[A]): A = m.combineAll(items)
}
