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
  "A Card" when {
    "set to Club nine" should {
      val card = Card(2, 7)
      "return Club as its Suit" in {
        card.getSuit() should be("Club")
      }
      "return nine as its rank" in {
        card.getValue() should be(9)
      }
    }
  }
}
