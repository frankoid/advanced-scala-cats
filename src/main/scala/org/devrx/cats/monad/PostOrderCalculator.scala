package org.devrx.cats.monad

import cats.data.State

object PostOrderCalculator {

  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" =>
      State[List[Int], Int] {
        case y :: x :: tail =>
          (x + y :: tail, x + y)
        case _ => sys.error("Stack underflow")
      }
    case "-" =>
      State[List[Int], Int] {
        case y :: x :: tail =>
          (x - y :: tail, x - y)
        case _ => sys.error("Stack underflow")
      }
    case "*" =>
      State[List[Int], Int] {
        case y :: x :: tail =>
          (x * y :: tail, x * y)
        case _ => sys.error("Stack underflow")
      }
    case "/" =>
      State[List[Int], Int] {
        case y :: x :: tail =>
          (x / y :: tail, x / y)
        case _ => sys.error("Stack underflow")
      }
    case s =>
      State[List[Int], Int] { oldStack =>
        val n = s.toInt
        (n :: oldStack, n)
      }
  }
}

// The solution given in the book calculates
// 4 3 -
// as
// 3 - 4 = -1
// I think this is probably wrong
object BookSolutionPostOrderCalculator {

  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] =
    sym match {
      case "+" => operator(_ + _)
      case "-" => operator(_ - _)
      case "*" => operator(_ * _)
      case "/" => operator(_ / _)
      case num => operand(num.toInt)
    }

  def operand(num: Int): CalcState[Int] =
    State[List[Int], Int] { stack =>
      (num :: stack, num)
    }

  def operator(func: (Int, Int) => Int): CalcState[Int] =
    State[List[Int], Int] {
      case a :: b :: tail =>
        val ans = func(a, b)
        (ans :: tail, ans)

      case _ =>
        sys.error("Fail!")
    }
}
