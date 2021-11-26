package de.htwg.se.romme
package controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.romme.model.Table
import de.htwg.se.romme.model.Deck
import de.htwg.se.romme.model.PlayerHands
import de.htwg.se.romme.model.Game
import de.htwg.se.romme.model.Player
import de.htwg.se.romme.model.Card

class GameCommandSpec() extends AnyWordSpec with Matchers {
  "A GameCommand" when {
    "created " should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val game = new Game(table, hand, deck)
      val controller = new Controller(game)
      hand.playerOneHand.addOne(Card(0, 0))
      val gC = new GameCommand(table, hand, deck, controller)
      "use doStep" in {
        gC.doStep
        controller.game.hand.playerOneHand.size should be(1)
      }
      "use undoStep" in {
        gC.undoStep
        controller.game.hand.playerOneHand.size should be(0)
      }
      "use redoStep" in {
        gC.redoStep
        controller.game.hand.playerOneHand.size should be(1)
      }
    }
  }
}
