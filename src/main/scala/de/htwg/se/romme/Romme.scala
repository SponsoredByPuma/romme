package de.htwg.se.romme

import de.htwg.se.romme.model.{
  Card,
  Deck,
  Player,
  PlayerHands,
  Drops,
  Game,
  State,
  Table
}
import de.htwg.se.romme.controller.Controller
import de.htwg.se.romme.aview.Tui
import scala.io.StdIn.readLine

object Romme {
  val deck = new Deck() // Deck-Instanz erstellt
  val table = new Table()
  val hand = new PlayerHands(table) // var hand = new PlayerHands(table)
  val game = Game(table, hand, deck)
  val controller = new Controller(game)
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
