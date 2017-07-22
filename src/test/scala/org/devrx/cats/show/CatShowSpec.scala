package org.devrx.cats.show

import org.scalatest.{Matchers, WordSpec}

class CatShowSpec extends WordSpec with Matchers {
  "Cat.show" should {
    "return a String describing the cat" in {
      import cats.syntax.show._

      val henry = Cat("Henry", 3, "ginger")
      val shown: String = henry.show
      shown shouldBe "Henry is a 3 year-old ginger cat."
    }
  }
}
