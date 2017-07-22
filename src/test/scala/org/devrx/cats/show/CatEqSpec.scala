package org.devrx.cats.show

import cats.instances.option._
import cats.syntax.eq._
import org.devrx.cats.Cat
import org.devrx.cats.instances.cat.eqForCat
import org.scalatest.{Matchers, WordSpec}

class CatEqSpec extends WordSpec with Matchers {
  // work around conflict between Cats === and Scalactic === (which we inherit via Matchers)
  // by overriding implicit convertToEqualizer with a non-implicit version
  override def convertToEqualizer[T](left: T): Equalizer[T] = super.convertToEqualizer(left)

  val cat1 = Cat("Garfield", 35, "orange and black")
  val cat2 = Cat("Heathcliff", 30, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  "Cat Eq instance" should {
    "return true when cats are equal" in {
      (Cat("James", 10, "black") === Cat("James", 10, "black")) shouldBe true
    }

    "return false when cats are not equal" in {
      cat1 === cat2 shouldBe false
    }
  }

  "comparing Option[Cat]s" should {
    "compile" in {
      optionCat1 === optionCat2 shouldBe false
      optionCat1 =!= optionCat2 shouldBe true
    }
  }
}
