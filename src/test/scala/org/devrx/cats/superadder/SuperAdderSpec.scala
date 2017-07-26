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

    "sum Orders" in {
      SuperAdder.add(List(Order(1.5, 23.0), Order(52.0, 1.0), Order(145.123, 12314.123))) shouldBe Order(1.5 + 52.0 + 145.123, 23.0 + 1.0 + 12314.123)
    }

    "return zero for empty list of Orders" in {
      SuperAdder.add(List.empty[Order]) shouldBe Order(0.0, 0.0)
    }
  }
}
