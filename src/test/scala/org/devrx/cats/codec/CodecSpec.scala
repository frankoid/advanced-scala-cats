package org.devrx.cats.codec

import org.scalatest.{Matchers, WordSpec}
import Codec.{decode, encode}
import CodecInstances._

class CodecSpec extends WordSpec with Matchers {
  "Box Codec" should {
    "encode Box[Int]s" in {
      encode(Box(123)) shouldBe "123"
    }

    "decode Strings into Box[Int]s" in {
      decode[Box[Int]]("123") shouldBe Some(Box(123))
    }
  }
}
