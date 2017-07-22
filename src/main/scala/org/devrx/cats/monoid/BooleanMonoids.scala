package org.devrx.cats.monoid

object OrMonoid extends Monoid[Boolean] {
  override def empty: Boolean = false

  override def combine(x: Boolean, y: Boolean): Boolean = x || y
}

object AndMonoid extends Monoid[Boolean] {
  override def empty: Boolean = true

  override def combine(x: Boolean, y: Boolean): Boolean = x && y
}

object XorMonoid extends Monoid[Boolean] {
  override def empty: Boolean = false

  override def combine(x: Boolean, y: Boolean): Boolean = x != y
}

object EqMonoid extends Monoid[Boolean] {
  override def empty: Boolean = true

  override def combine(x: Boolean, y: Boolean): Boolean = x == y
}
