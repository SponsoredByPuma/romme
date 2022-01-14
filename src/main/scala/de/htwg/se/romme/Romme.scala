package de.htwg.se.romme

import de.htwg.se.romme.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.romme.model.modelComponent.gameComponent.GameInterface
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl._
import de.htwg.se.romme.aview.gui.SwingGui
import de.htwg.se.romme.aview.Tui
import scala.io.StdIn.readLine

import com.google.inject.Guice
import de.htwg.se.romme.controller.controllerComponent.ControllerInterface

object Romme {

  val deck = new Deck() // Deck-Instanz erstellt
  val table = new Table()
  val hand = new PlayerHands(table) // var hand = new PlayerHands(table)
  val hand2 = new PlayerHands(table)
  val player = new Player("Player 1", hand, table)
  val player2 = new Player("Player 2", hand2, table)
  val game = Game(table, player, player2, deck)

  //val injector = Guice.createInjector(new RommeModule)
  //val controller = injector.getInstance(classOf[ControllerInterface])
  val controller = new Controller(game)
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  //controller.notifyObservers

  def main(args: Array[String]): Unit = {

    var input: String = ""

    input = readLine()
    while (input != "quit") {
      tui.processInputReadLine(input)
      input = readLine()
    }
  }
}
