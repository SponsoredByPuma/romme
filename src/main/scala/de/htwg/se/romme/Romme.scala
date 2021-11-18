package de.htwg.se.romme

import de.htwg.se.romme.model.{Card, Deck, Player, PlayerHands}
import de.htwg.se.romme.controller.Controller
import de.htwg.se.romme.aview.Tui
import scala.io.StdIn.readLine

object Romme {
  val controller = new Controller()
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {

    var input: String = ""

    input = readLine()
    while (input != "quit") {
      tui.processInputReadLine(input)
      input = readLine()
    }
  }
}
