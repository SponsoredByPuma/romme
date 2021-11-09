package de.htwg.se.romme

package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class DeckSpec extends AnyWordSpec {

  "A Deck " when {
    "created" should {
      val deck = Deck()
      "be 110 cards" in {
        deck.createNewDeck()
        deck.deckList.size should be(110)
      }
      "be 109 cards, after you picked one card up" in {
        deck.drawFromDeck()
        deck.deckList.size should be(109)
      }

    }
  }

}
