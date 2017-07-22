package org.devrx.cats.monoid

class IntersectSemigroup[A] extends Semigroup[Set[A]] {
  override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
}

// Not really a semigroup - TODO test case that demonstrates this
class DifferenceSemigroup[A] extends Semigroup[Set[A]] {
  override def combine(x: Set[A], y: Set[A]): Set[A] = x diff y
}
