package org.devrx.cats.show

import cats.Show
import cats.syntax.show._

final case class Cat(
  name: String,
  age: Int,
  color: String
)

object Cat {
  implicit val showForCat = Show.show[Cat] { cat =>
    import cats.instances.string.catsStdShowForString
    import cats.instances.int.catsStdShowForInt
    s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat."
  }
}

object CatDemo {
  def main(args: Array[String]): Unit = {
    val henry = Cat("Henry", 3, "Ginger")
    println(henry.show)
  }
}
