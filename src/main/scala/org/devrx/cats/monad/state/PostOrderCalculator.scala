package org.devrx.cats.monad.state

import cats.data.State

object PostOrderCalculator {

  type CalcState[A] = State[List[Int], A]

  // Versions that throw an exception given an empty input list -------------
  def evalAll(input: List[String]): CalcState[Int] = input match {
    case head :: Nil => evalOne(head)
    case head :: tail => evalOne(head).flatMap(_ => evalAll(tail))
  }

  // Versions that default to 0 given an empty input list -------------------
//  def evalAll(input: List[String]): CalcState[Int] = {
//    def evalAllMain(input: List[String]): CalcState[Int] = input match {
//      case head :: Nil => evalOne(head)
//      case head :: tail => evalOne(head).flatMap(_ => evalAllMain(tail))
//    }
//
//    evalAllMain("0" :: input)
//  }

  // fold version
//  def evalAll(input: List[String]): CalcState[Int] =
//    input.foldLeft(0.pure[CalcState])((state: CalcState[Int], s: String) => state.flatMap(_ => evalOne(s)))

  // sample answer
//  def evalAll(input: List[String]): CalcState[Int] =
//  input.foldLeft(0.pure[CalcState]) { (a, b) =>
//    a flatMap (_ => evalOne(b))
//  }

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
