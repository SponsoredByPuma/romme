package de.htwg.se.romme

package aview

import controller.Controller
import model.{Card, Deck, Player, PlayerHands, Table}
import util.Observable
import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer

class Tui(controller: Controller) extends de.htwg.se.romme.util.Observer {
  controller.add(this)

  def processInputReadLine(input: String): Unit = {
    input match {
      case "quit"    =>
      case "new"     => controller.gameStart()
      case "pick"    => controller.pickUpACard()
      case "graveYard" => controller.pickUpGraveYard() // picking up the graveYard Card
      case "drop"    => 
        println("Which card would you like to drop ?")
        var index = readLine().toInt
        controller.dropASpecificCard(index)
      case "dropM" => 
        var amount = 0
        while(amount < 3 || amount >= controller.hand.playerOneHand.size) 
          print("How many Cards would you like to drop ?")
          amount = readLine.toInt
        var list : ListBuffer[Integer] = new ListBuffer()
        var scanner = ""
        while(amount > 0)
          println("Which Card would you like to drop ? It must be something between 0 and " + (controller.hand.playerOneHand.size - 1))
          scanner = readLine
          list.addOne(scanner.toInt)
          amount = amount - 1
        println("Would you like to drop them by Suit(0) or by Order(1) ?")
        var dec = readLine.toInt
        controller.dropMultipleCards(list,dec)
      case "show"    => controller.showCards()
      case "showTable" => controller.showTable()
      case "sort" => controller.sortPlayersCards()
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
  // test

  override def update: Unit = println() // showCards()
  override def updated: Boolean = true
}
