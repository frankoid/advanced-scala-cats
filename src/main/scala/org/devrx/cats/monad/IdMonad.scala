package org.devrx.cats.monad

object IdMonad {

  implicit def idMonad: Monad[Id] = new Monad[Id] {
    override def pure[A](a: A): Id[A] = a

    override def flatMap[A, B](value: Id[A])(func: (A) => Id[B]): Id[B] =
      func(value)
  }

}
