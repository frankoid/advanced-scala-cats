package org.devrx.cats.monad.reader

import cats.Monad
import cats.data.Reader

case class Db(
  usernames: Map[Int, String],
  passwords: Map[String, String]
)

object Db {

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(
    username: String,
    password: String
  ): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  def checkLogin(
    userId: Int,
    password: String
  ): DbReader[Boolean] =
    for {
      maybeUsername <- findUsername(userId)
      passwordCorrect <- maybeUsername
        .map(username => checkPassword(username, password))
        .getOrElse(Monad[DbReader].pure(false))
    } yield passwordCorrect

}
