import org.devrx.cats.monad.Db
import org.devrx.cats.monad.Db._

val db = Db(
  Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  ),
  Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret"
  )
)

checkLogin(1, "zerocool").run(db)
checkLogin(1, "zeroNotcool").run(db)

checkLogin(4, "davinci").run(db)
