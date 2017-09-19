package org.devrx.cats.monad.state

import cats.data.State
import cats.syntax.applicative._

object PostOrderCalculator {

  type CalcState[A] = State[List[Int], A]

  // evalAll(List("4", "3", "-"))
  // evalOne("4").flatMap(_ => evalAll(List("3", "-"))
  // evalOne("4").flatMap(_ => evalOne("3").flatMap(_ => evalAll(List("-"))))
  // evalOne("4").flatMap(_ => evalOne("3").flatMap(_ => evalOne("-").flatMap(_ => evalAll(Nil))))
  // evalOne("4").flatMap(_ => evalOne("3").flatMap(_ => evalOne("-").flatMap(_ => 0.pure[CalcState])))

  def evalAll(input: List[String]): CalcState[Int] = input match {
    case Nil => 0.pure[CalcState]
//    case head :: Nil => evalOne(head)
    case head :: tail => evalOne(head).flatMap(_ => evalAll(tail))
  }

  // fold version
//  def evalAll(input: List[String]): CalcState[Int] =
//    input.foldRight(0.pure[CalcState])((s, state) => state.flatMap(_ => evalOne(s)))

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case s => operand(s.toInt)
  }

  def operand(num: Int): CalcState[Int] =
    State[List[Int], Int] { oldStack =>
      (num :: oldStack, num)
    }

  def operator(func: (Int, Int) => Int): CalcState[Int] =
    State[List[Int], Int] {
      case y :: x :: tail =>
        val ans = func(x, y)
        (ans :: tail, ans)
      case _ => sys.error("Stack underflow")
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
