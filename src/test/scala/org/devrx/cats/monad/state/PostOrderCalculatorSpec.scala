package org.devrx.cats.monad.state

import org.devrx.cats.monad.state.PostOrderCalculator.evalAll
import org.scalatest.{Matchers, WordSpec}

class PostOrderCalculatorSpec extends WordSpec with Matchers {

  "evalAll" should {
    "evaluate post-order expressions" in {
      val allProgram = evalAll(List("1", "2", "+", "3", "*"))

      allProgram.runA(Nil).value shouldBe 9
    }

    "throw an exception for empty list because the value of evaluating an empty post-order expression is undefined" in {
      intercept[Exception] {
        val input: List[String] = List.empty
        evalAll(input).run(Nil).value
      }
    }
  }
}
