package de.htwg.se.romme

package aview

import de.htwg.se.romme.controller.Controller
import model.{Card, Deck, Player, PlayerHands}

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class TuiSpec extends AnyWordSpec {
  "A Romme Tui" should {
    val controller = new Controller()
    val tui = new Tui(controller)
    "create a new Game on input 'new' and the deck size should be 97" in {
      tui.processInputReadLine("new")
      controller.deck.deckList.size should be(97)
    }
    "create a new Game on input 'new' and the player Hands should be 13 " in {
      controller.hand.playerOneHand.size should be(13)
    }
    "pick up a new Card on input 'pick' and the deck size should be 96 " in {
      tui.processInputReadLine("pick")
      controller.deck.deckList.size should be(96)
    }
    "pick up a new Card on input 'pick' and the player Hands should be 96 " in {
      controller.hand.playerOneHand.size should be(14)
    }
    "shouldn't show Victory on input 'victory' aslong as there are still cards in the player Hands " in {
      tui.processInputReadLine("victory")
      controller.victory() should be(false)
    }
    "drop a card from the players Hand on input 'drop' " in {
      tui.processInputReadLine("drop")
      controller.hand.playerOneHand.size should be(13)
    }
    "pick up the graveYard Card and the player Hands should be 14" in {
      tui.processInputReadLine("graveYard")
      controller.hand.playerOneHand.size should be(14)
    }
  }
}
