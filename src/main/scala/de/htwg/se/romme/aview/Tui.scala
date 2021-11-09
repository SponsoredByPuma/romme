package de.htwg.se.romme

package aview

import controller.Controller
import model.{Card, Deck, Player, PlayerHands, Table}
import util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)

  def processInputReadLine(input: String): Unit = {
    input match {
      case "quit"    =>
      case "new"     => controller.gameStart()
      case "pick"    => controller.pickUpACard()
      case "drop"    => controller.dropASpecificCard()
      case "show"    => controller.showCards()
      case "dropTest" => controller.dropASpecificCardTEST() // only for testing can be deleted
      case "victory" => 
        val victory = controller.victory()
        if (victory == true) 
          println("Victory ! You have won the Game !")
        else
          println("You havent won the Game yet !")
        end if  
    }
  }

  override def update: Unit = println(controller.showCards())
}
