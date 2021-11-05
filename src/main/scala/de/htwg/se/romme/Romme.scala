package de.htwg.se.romme

import de.htwg.se.romme.model.{Card, Deck, Player, PlayerHands}
import de.htwg.se.romme.controller.Controller
import scala.io.StdIn.readLine

object Romme {
  def main(args: Array[String]): Unit = {
    val controller = new Controller()

    controller.gameStart()
    controller.pickUpACard()
    println("Which card would you like to drop ?")
    var tmp = readLine()
    controller.dropASpecificCard(tmp.toInt)
  }
}
