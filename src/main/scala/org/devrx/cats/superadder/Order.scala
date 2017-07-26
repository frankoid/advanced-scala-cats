package org.devrx.cats.superadder

import cats.Monoid
import cats.instances.double._
import cats.syntax.semigroup._

case class Order(totalCost: Double, quantity: Double)

object Order {
  implicit val orderMonoid = new Monoid[Order] {
    override def empty: Order = Order(Monoid[Double].empty, Monoid[Double].empty)
    override def combine(x: Order, y: Order): Order =
      Order(x.totalCost |+| y.totalCost, x.quantity |+| y.quantity)
  }
}
