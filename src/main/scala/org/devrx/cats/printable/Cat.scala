package org.devrx.cats.printable

final case class Cat(
  name: String,
  age: Int,
  color: String
)

object Cat {
  implicit val printable = new Printable[Cat] {
    import PrintableInstances._
    override def format(cat: Cat): String = s"${Printable.format(cat.name)} is a ${Printable.format(cat.age)} year-old ${Printable.format(cat.color)} cat."
  }
}

object CatDemo {
  def main(args: Array[String]): Unit = {
    import PrintableSyntax._

    val henry = Cat("Henry", 3, "Ginger")
    henry.print
  }
}
