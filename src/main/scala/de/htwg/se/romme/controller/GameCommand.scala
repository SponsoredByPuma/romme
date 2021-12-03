package de.htwg.se.romme.controller

import de.htwg.se.romme.model.Game
import de.htwg.se.romme.util.Command
import de.htwg.se.romme.model.Table
import de.htwg.se.romme.model.Deck
import de.htwg.se.romme.model.PlayerHands
import de.htwg.se.romme.model.Player
import de.htwg.se.romme.model.Card

class GameCommand(gaming: Game, controller: Controller) extends Command {
  override def doStep: Unit = controller.game = controller.game.set(
    gaming.table,
    gaming.player,
    gaming.player2,
    gaming.deck
  )

  override def undoStep: Unit = {
    var t = new Table()
    var h = new PlayerHands(gaming.table)
    var d = new Deck()
    var c: Card = gaming.player.hands.playerOneHand.last
    h = gaming.player.hands
    h.playerOneHand.remove(h.playerOneHand.size - 1)
    d = gaming.deck
    c +=: d.deckList
    controller.game = controller.game.set(t, gaming.player, gaming.player2, d)
  }

  override def redoStep: Unit = {
    var t = new Table()
    var h = new PlayerHands(gaming.table)
    var d = new Deck()
    var c: Card = gaming.deck.deckList(0)
    d = gaming.deck
    d.deckList.remove(0)
    h = gaming.player.hands
    h.playerOneHand.append(c)
    controller.game = controller.game.set(t, gaming.player, gaming.player2, d)
  }
}
