package org.devrx.cats.monad

import cats.Eval

object EvalFoldRight {

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
    def evalFoldRight(as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
      as match {
        case head :: tail =>
          Eval.defer(fn(head, evalFoldRight(tail, acc)(fn)))
        case Nil =>
          acc
      }

    evalFoldRight(as, Eval.now(acc))((a, evalB) =>
      evalB.flatMap((b: B) => Eval.now(fn(a, b)))).value
  }
}
