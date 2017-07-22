package org.devrx.cats.instances

import cats.{Eq, Show}
import org.devrx.cats.{Cat, Printable}

object cat {
  implicit val printableForCat = new Printable[Cat] {
    import org.devrx.cats.instances.int._
    import org.devrx.cats.instances.string._
    override def format(cat: Cat): String = s"${Printable.format(cat.name)} is a ${Printable.format(cat.age)} year-old ${Printable.format(cat.color)} cat."
  }

  implicit val showForCat = Show.show[Cat] { cat =>
    import cats.instances.int.catsStdShowForInt
    import cats.instances.string.catsStdShowForString
    import cats.syntax.show._

    s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat."
  }

  implicit val eqForCat = Eq.instance[Cat] { (a, b) => a == b }
}
