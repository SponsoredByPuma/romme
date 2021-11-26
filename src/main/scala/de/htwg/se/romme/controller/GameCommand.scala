package de.htwg.se.romme.controller

import de.htwg.se.romme.model.Game
import de.htwg.se.romme.util.Command
import de.htwg.se.romme.model.Table
import de.htwg.se.romme.model.Deck
import de.htwg.se.romme.model.PlayerHands
import de.htwg.se.romme.model.Player

class GameCommand(
    table: Table,
    hand: PlayerHands,
    deck: Deck,
    controller: Controller
) extends Command {
  override def doStep: Unit = controller.game =
    controller.game.set(table, hand, deck)

  override def undoStep: Unit = {
    val t = Table()
    controller.game = controller.game.set(t, new PlayerHands(t), new Deck())
  }

  override def redoStep: Unit = controller.game =
    controller.game.set(table, hand, deck)

}
