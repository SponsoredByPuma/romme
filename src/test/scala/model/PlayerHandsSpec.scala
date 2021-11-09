package de.htwg.se.romme

package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerHandsSpec extends AnyWordSpec {
  "A player Hands" when {
    "starting the game " should {
      val deck = Deck()
      deck.createNewDeck()
      val hands = PlayerHands()
      "be 13 Cards " in {
        hands.draw13Cards(deck)
        hands.playerOneHand.size should be(13)
      }
    }
  }
}
