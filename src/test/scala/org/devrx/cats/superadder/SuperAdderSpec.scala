package org.devrx.cats.superadder

import cats.instances.int._
import cats.instances.option._
import org.scalatest.{Matchers, WordSpec}

class SuperAdderSpec extends WordSpec with Matchers {

  "add" should {
    "sum ints" in {
      SuperAdder.add(List(5, 10, 1)) shouldBe 16
    }

    "return zero for empty list" in {
      SuperAdder.add(List.empty[Int]) shouldBe 0
    }

    "sum Option[Int]s" in {
      SuperAdder.add(List(None, Some(5), None, Some(10), Some(6))) shouldBe Some(21)
    }

    "return None for empty list" in {
      SuperAdder.add(List.empty[Option[Int]]) shouldBe None
    }

    "return None when all Option[Int]s are None" in {
      SuperAdder.add(List(None, None): List[Option[Int]]) shouldBe None
    }
  }
}
