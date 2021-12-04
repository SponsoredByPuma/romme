package de.htwg.se.romme

package aview

import controller.Controller
import model.{Card, Deck, Player, PlayerHands, Table}
import util.Observable
import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer

class Tui(controller: Controller) extends de.htwg.se.romme.util.Observer {
  controller.add(this)
  var player1Turn: Boolean = true //
  

  def processInputReadLine(input: String): Unit = {
    input match {
      case "quit"    =>
      case "switch" => player1Turn = !player1Turn
      case "new"     => controller.gameStart()
      case "pick"    => controller.pickUpACard(player1Turn)
      case "graveYard" => controller.pickUpGraveYard(player1Turn)
      case "add" => 
        println("Which card would you like to add ?")
        var cardIndex = readLine().toInt
        println("Which Set would you like to change expand ?")
        var listIndex = readLine().toInt
        controller.addCard(cardIndex,listIndex,player1Turn)
      case "undo" => controller.undo
      case "redo" => controller.redo
      case "drop"    => 
        println("Which card would you like to drop ?")
        var index = readLine().toInt
        controller.dropASpecificCard(index,player1Turn)
      case "dropM" => 
        var amount = 0
        if(player1Turn)
          while(amount < 3 || amount >= controller.game.player.hands.playerOneHand.size) 
            print("How many Cards would you like to drop ?")
            amount = readLine.toInt
        else
          while(amount < 3 || amount >= controller.game.player2.hands.playerOneHand.size) 
            print("How many Cards would you like to drop ?")
            amount = readLine.toInt
        end if
        var list : ListBuffer[Integer] = new ListBuffer()
        var scanner = ""
        while(amount > 0)
          println("Which Card would you like to drop ?")
          scanner = readLine
          list.addOne(scanner.toInt)
          amount = amount - 1
        println("Would you like to drop them by Suit(0) or by Order(1) ?")
        var dec = readLine.toInt
        controller.dropMultipleCards(list,dec,player1Turn)
      case "show"    => controller.showCards(player1Turn)
      case "joker" => 
        println("Which Card would you like to drop ?")
        var cardInput = readLine().toInt
        println("Which Set would you like to change ?")
        var setInput = readLine().toInt
        controller.takeJoker(setInput,cardInput,player1Turn)
      case "showTable" => controller.showTable(player1Turn)
      case "sort" => controller.sortPlayersCards(player1Turn)
      case "victory" => 
        val victory = controller.victory(player1Turn)
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
