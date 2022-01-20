package de.htwg.se.romme

package aview

import controller.controllerComponent._
import controller.controllerComponent.controllerBaseImpl._
import util.Observable
import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer
import scala.swing.Reactor
import scala.compiletime.ops.string

class Tui(controller: ControllerInterface) extends Reactor {
  listenTo(controller)
  
  def processInputReadLine(input: String): Unit = {
    input match {
      case "quit"    => System.exit(0)
      case "switch" => controller.switch
      case "new"     => controller.gameStart
      case "pick"    => controller.pickUpACard
      case "graveYard" => controller.pickUpGraveYard
      case "add" => 
        println("Which card would you like to add ?")
        var cardIndex = readLine().toInt
        println("Which Set would you like to expand ?")
        var listIndex = readLine().toInt
        controller.addCard(cardIndex,listIndex)
      case "undo" => controller.undo
      case "redo" => controller.redo
      case "drop"    => 
        println("Which card would you like to drop ?")
        var index = readLine().toInt
        controller.dropASpecificCard(index)
      case "dropM" => 
        var amount = 0
        if(controller.player1Turn)
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
        var tt: ListBuffer[Integer] = new ListBuffer()
        tt = controller.checkForJoker(list)
        if(tt.isEmpty)
          controller.dropMultipleCards(list,dec,false)
        else
          var stringList: ListBuffer[String] = ListBuffer()
          if(dec == 0) // nach Suit
            for (x <- 0 to tt.size - 1)
              println("Which Suit should your Joker have ?")
              var input = readLine()
              stringList.addOne(input)
            controller.replaceCardSuit(tt,stringList)
            controller.dropMultipleCards(list,dec,true)
          else
            for (x <- 0 to tt.size - 1)
              println("Which Rank should your Joker have ?")
              var input = readLine()
              stringList.addOne(input)
            controller.replaceCardOrder(tt,stringList)
            controller.dropMultipleCards(list,dec,true)
          end if
      case "show"    => print(controller.showCards)
      case "joker" => 
        println("Which Card would you like to drop ?")
        var cardInput = readLine().toInt
        println("Which Set would you like to change ?")
        var setInput = readLine().toInt
        controller.takeJoker(setInput,cardInput)
      case "showTable" => print(controller.showTable)
      case "sort" => controller.sortPlayersCards
      case "victory" => 
        val victory = controller.victory
        if (victory == true) 
          println("Victory ! You have won the Game !")
        else
          println("You havent won the Game yet !")
        end if
      case "load" => controller.load
      case "save" => controller.save
      case _ =>  
    }
  }

  reactions += {
    case event: showPlayerCards => printCards
    case event: showPlayerTable => printTable
  }

  def printCards: Unit = {
    println(controller.showCards)
  }

  def printTable: Unit = {
    println(controller.showTable)
  }
}
