package de.htwg.se.romme

import com.google.inject.{AbstractModule, Guice, Inject}
import com.google.inject.TypeLiteral
import net.codingwell.scalaguice.ScalaModule

import de.htwg.se.romme.model.modelComponent.gameComponent.GameInterface
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl._

import de.htwg.se.romme.model.modelComponent.dropsComponent.DropsInterface
import de.htwg.se.romme.model.modelComponent.dropsComponent.dropsBaseImpl.Drops

import de.htwg.se.romme.controller.controllerComponent.ControllerInterface
import de.htwg.se.romme.controller.controllerComponent.controllerBaseImpl._

class RommeModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).to(classOf[Controller])
    val deck = new Deck() // Deck-Instanz erstellt
    val table = new Table()
    val hand = new PlayerHands(table) // var hand = new PlayerHands(table)
    val hand2 = new PlayerHands(table)
    val player = new Player("Player 1", hand, table)
    val player2 = new Player("Player 2", hand2, table)
    bind(classOf[GameInterface]).toInstance(Game(table, player, player2, deck))
    //bind(classOf[DropsInterface]).to(classOf[Drops])
  }
}
