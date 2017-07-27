package org.devrx.cats

import org.scalatest.{Matchers, WordSpec}

class PrintableSpec extends WordSpec with Matchers {
  "contramap" should {
    "transform a Printable[A] into a Printable[B]" in {
      val pA = new Printable[Double] {
        override def format(a: Double): String = a.toString
      }

      def fB2A(b: Int): Double = b

      val pB: Printable[Int] = pA.contramap(fB2A)
      pB.format(42) shouldBe "42.0"
    }
  }
}
