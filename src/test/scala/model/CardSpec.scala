package de.htwg.se.romme

package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class CardSpec extends AnyWordSpec {
  "A Card" when {
    "set to Heart eight " should {
      val card = Card(0, 6)
      "be Heart eight" in {
        card.getCardName() should be("Heart", "eight")
      }
    }
    "set to Joker " should {
      val card = Card(4, 13)
      "be Joker " in {
        card.getCardName() should be("Joker", "")
      }
    }
  }
}
