package org.devrx.cats.codec

import scala.util.Try

trait Codec[A] { codecA =>
  def encode(value: A): String
  def decode(value: String): Option[A]

  def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
    override def encode(value: B): String = codecA.encode(enc(value))

    override def decode(value: String): Option[B] = codecA.decode(value).map(dec)
  }
}

object Codec {
  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): Option[A] =
    c.decode(value)
}

case class Box[A](value: A)

object CodecInstances {
  implicit val intCodec = new Codec[Int] {
    override def encode(value: Int): String = value.toString

    override def decode(value: String): Option[Int] =
      (Try {
        Some(value.toInt)
      } recover {
        case _: NumberFormatException => None
      }).get
  }

  implicit def BoxCodec[A](implicit codec: Codec[A]): Codec[Box[A]] = codec.imap(
    dec = Box(_),
    enc = _.value
  )
}
