package org.devrx.cats.monad.state

import org.devrx.cats.monad.state.PostOrderCalculator.evalAll
import org.scalatest.{Matchers, WordSpec}

class PostOrderCalculatorSpec extends WordSpec with Matchers {

  "evalAll" should {
    "evaluate post-order expressions" in {
      val allProgram = evalAll(List("1", "2", "+", "3", "*"))

      allProgram.runA(Nil).value shouldBe 9
    }

    "???" in {
      val input: List[String] = List.empty
      println(evalAll(input).run(Nil).value)
    }
  }
}
