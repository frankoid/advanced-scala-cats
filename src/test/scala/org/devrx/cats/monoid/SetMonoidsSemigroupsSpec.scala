package org.devrx.cats.monoid

import org.scalatest.{Matchers, WordSpec}

class SetMonoidsSemigroupsSpec extends WordSpec with Matchers {
  "UnionMonoid" should {
    "be a monoid" in {
      shouldBeAMonoid(new UnionMonoid[Int])
    }
  }

  // Not really a semigroup - TODO test case that demonstrates this
  "DifferenceSemigroup" should {
    "be a semigroup" in {
      shouldBeASemigroup(new DifferenceSemigroup[Int])
    }
  }

  "IntersectSemigroup" should {
    "be a semigroup" in {
      shouldBeASemigroup(new IntersectSemigroup[Int])
    }
  }

  private def shouldBeAMonoid(implicit m: Monoid[Set[Int]]) = {
    shouldBeASemigroup

    identityLaw(Set.empty[Int]) shouldBe true
    identityLaw(Set(1, 2, 3)) shouldBe true
  }

  private def shouldBeASemigroup(implicit m: Semigroup[Set[Int]]) = {
    associativeLaw(Set.empty[Int], Set(1, 2), Set(3)) shouldBe true
    associativeLaw(Set(1, 2), Set(3, 4), Set(5, 6)) shouldBe true
    associativeLaw(Set(1, 2, 3), Set(3, 4, 5), Set(5, 6, 7)) shouldBe true
  }

  private def associativeLaw[A](x: A, y: A, z: A)
    (implicit m: Semigroup[A]): Boolean =
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)

  private def identityLaw[A](x: A)
    (implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
    (m.combine(m.empty, x) == x)
  }
}
