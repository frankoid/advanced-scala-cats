package org.devrx.cats.monoid

import org.scalatest.{Matchers, WordSpec}

class BooleanMonoidsSpec extends WordSpec with Matchers {
  "OrMonoid" should {
    "be a monoid" in {
      shouldBeAMonoid(OrMonoid)
    }
  }

  "AndMonoid" should {
    "be a monoid" in {
      shouldBeAMonoid(AndMonoid)
    }
  }

  "XorMonoid" should {
    "be a monoid" in {
      shouldBeAMonoid(XorMonoid)
    }
  }

  "EqMonoid" should {
    "be a monoid" in {
      shouldBeAMonoid(EqMonoid)
    }
  }

  private def shouldBeAMonoid(implicit m: Monoid[Boolean]) = {
    associativeLaw(false, false, false) shouldBe true
    associativeLaw(false, false, true) shouldBe true
    associativeLaw(false, true, false) shouldBe true
    associativeLaw(false, true, true) shouldBe true
    associativeLaw(true, false, false) shouldBe true
    associativeLaw(true, false, true) shouldBe true
    associativeLaw(true, true, false) shouldBe true
    associativeLaw(true, true, true) shouldBe true

    identityLaw(false) shouldBe true
    identityLaw(true) shouldBe true
  }

  private def associativeLaw[A](x: A, y: A, z: A)
    (implicit m: Monoid[A]): Boolean =
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)

  private def identityLaw[A](x: A)
    (implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
    (m.combine(m.empty, x) == x)
  }
}
