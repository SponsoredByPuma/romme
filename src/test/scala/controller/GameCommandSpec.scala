package de.htwg.se.romme
package controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model.modelComponent.gameBaseImpl._
import controller.controllerComponent.controllerBaseImpl.Controller
import controller.controllerComponent.controllerBaseImpl.GameCommand
class GameCommandSpec() extends AnyWordSpec with Matchers {
  "A GameCommand" when {
    "created " should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val hand2 = new PlayerHands(table)
      val player = new Player("Player 1", hand, table)
      val player2 = new Player("Player 2", hand2, table)
      val game = new Game(table, player, player2, deck)
      val controller = new Controller(game)
      hand.playerOneHand.addOne(Card(0, 0))
      val gC = new GameCommand(game, controller)
      "use doStep" in {
        gC.doStep
        controller.game.player.hands.playerOneHand.size should be(1)
      }
      "use undoStep" in {
        gC.undoStep
        controller.game.player.hands.playerOneHand.size should be(0)
      }
      "use redoStep" in {
        gC.redoStep
        controller.game.player.hands.playerOneHand.size should be(1)
      }
    }
  }
}
