import org.devrx.cats.monad.state.PostOrderCalculator._

val program = for {
  _   <- evalOne("4")
  _   <- evalOne("3")
  ans <- evalOne("-")
} yield ans

program.runA(Nil).value