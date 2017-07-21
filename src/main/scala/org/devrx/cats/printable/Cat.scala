package org.devrx.cats.printable

final case class Cat(
  name: String,
  age: Int,
  color: String
)

object Cat {
  implicit val printable = new Printable[Cat] {
    override def format(cat: Cat): String = s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
  }
}

object CatDemo {
  def main(args: Array[String]): Unit = {
    val henry = Cat("Henry", 3, "Ginger")
    Printable.print(henry)
  }
}
