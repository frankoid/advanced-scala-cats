package org.devrx.cats.superadder

import org.scalatest.{Matchers, WordSpec}

class SuperAdderSpec extends WordSpec with Matchers {

  "add" should {
    "return zero for empty list" in {
      SuperAdder.add(List.empty) shouldBe 0
    }

    "return sum" in {
      SuperAdder.add(List(5, 10, 1)) shouldBe 16
    }
  }
}
