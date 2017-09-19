import org.devrx.cats.monad.state.PostOrderCalculator._

//val program = for {
//  _   <- evalOne("4")
//  _   <- evalOne("3")
//  ans <- evalOne("-")
//} yield ans

val program = evalOne("4")
  .flatMap{_ => evalOne("3")
    .flatMap {_ => evalOne("-")}}

program.runA(Nil).value

evalAll(List("2", "2", "+", "3", "-")).runA(Nil).value

val allProgram = evalAll(List("1", "2", "+", "3", "*"))

allProgram.runA(Nil).value

val input: List[String] = List.empty
evalAll(input).run(Nil).value
