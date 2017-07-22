package org.devrx.cats.monoid

class IntersectSemigroup[A] extends Semigroup[Set[A]] {
  override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
}
