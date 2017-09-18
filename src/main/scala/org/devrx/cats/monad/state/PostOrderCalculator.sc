import org.devrx.cats.monad.state.PostOrderCalculator._

val program = for {
  _   <- evalOne("4")
  _   <- evalOne("3")
  ans <- evalOne("-")
} yield ans

program.runA(Nil).value

evalAll(List("2", "2", "+", "3", "-")).runA(Nil).value

val allProgram = evalAll(List("1", "2", "+", "3", "*"))

allProgram.runA(Nil).value
