package org.devrx.cats.monad

import org.devrx.cats.monad.EvalFoldRight.foldRight
import org.scalatest.{Matchers, WordSpec}

class EvalFoldRightSpec extends WordSpec with Matchers {

  "foldRight" should {
    "fold small things" in {
      foldRight(List("first", "second", "third"), "acc") {
        (a, b) => s"$a $b"
      } shouldBe "first second third acc"
    }

    "fold big things" in {
      foldRight((1 to 10000).toList, 0) { (a, b) => a + b } shouldBe 50005000
    }
  }
}
