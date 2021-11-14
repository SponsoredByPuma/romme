package de.htwg.se.romme

package aview

import controller.Controller
import model.{Card, Deck, Player, PlayerHands, Table}
import util.Observable

class Tui(controller: Controller) extends de.htwg.se.romme.util.Observer {
  controller.add(this)

  def processInputReadLine(input: String): Unit = {
    input match {
      case "quit"    =>
      case "new"     => controller.gameStart()
      case "pick"    => controller.pickUpACard()
      case "graveYard" => controller.pickUpGraveYard() // picking up the graveYard Card
      case "drop"    => controller.dropASpecificCard()
      case "dropM" => controller.dropMultipleCards() // Test
      case "show"    => controller.showCards()
      case "showTable" => controller.showTable()
      case "sort" => controller.sortPlayersCards() // sortiere die Karten auf der Hand
      case "dropTest" => controller.dropASpecificCardTEST() // only for testing can be deleted
      case "victory" => 
        val victory = controller.victory()
        if (victory == true) 
          println("Victory ! You have won the Game !")
        else
          println("You havent won the Game yet !")
        end if
      case _ =>   
    }
  }

  override def update: Unit = println() // showCards()
  override def updated: Boolean = true
}
