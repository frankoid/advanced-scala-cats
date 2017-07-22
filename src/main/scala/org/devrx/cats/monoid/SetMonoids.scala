package org.devrx.cats.monoid

class UnionMonoid[A] extends Monoid[Set[A]] {
  override def empty: Set[A] = Set.empty[A]

  override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
}
