package de.htwg.se.romme
package controller

import model.{Card, Deck, Player, PlayerHands,Table}
import _root_.de.htwg.se.romme.util.Observable
import scala.io.StdIn.readLine
import util.Observable
import scala.collection.mutable.ListBuffer

class Controller() extends Observable {
  val deck = new Deck() // Deck-Instanz erstellt
  val table = new Table()
  val hand = new PlayerHands(table)
  

  def gameStart(): Unit = {
    deck.createNewDeck() // neues Deck erstellen
    hand.draw13Cards(deck)
    var tmp = 0
    println()
    notifyObservers
  }

  def pickUpGraveYard(): Unit = {
    hand.playerOneHand.addOne(table.grabGraveYard())
    notifyObservers
  }

  def pickUpACard(): Unit = {
    hand.playerOneHand.addOne(deck.drawFromDeck())
    notifyObservers
  }

  def dropASpecificCard(): Unit = {
    println("Which card would you like to drop ?")
    var index = readLine()
    hand.dropASingleCard(index.toInt)
    println()
    notifyObservers
  }
  def dropASpecificCardTEST(): Unit = { // Only for testing
    hand.dropASingleCard(0)
    println()
    notifyObservers
  }

  def dropMultipleCards() : Unit = {
    var scanner = " "
    var list : ListBuffer[Integer] = new ListBuffer()
    var counter = 0
    while (counter < 5)
      println("Which card would you like to drop ? It must be something between 0 and " + (hand.playerOneHand.size - 1))
      scanner = readLine()
      list.addOne(scanner.toInt)
      counter = counter + 1 
    if(hand.dropCardsOnTable(list) == true)
      list.sorted // sotiere die Liste
      for(counter <- 0 to list.size - 1) { // gehe die Liste durch
        // falls die Zahl 0 < 12 ist müssen die restlichen Cards um 1 verringert werden, da bei remove eins weggenommen wird
        if (list(counter) < hand.playerOneHand.size - 1) 
          for (counter <- counter + 1 to list.size - 1) { // go through the next inputs
            list(counter) = list(counter) - 1 // decrement the next input for one
          }
        end if
        hand.playerOneHand.remove(list(counter)) // remove the Card
      }
      println("it worked")
    end if
    notifyObservers
  }

  def sortPlayersCards(): Unit = {
    notifyObservers
    println("Press 1 to sort by rank or something else to sort by Suit !")
    val idx = readLine()
    hand.sortMyCards(idx.toInt)
  }

  def victory(): Boolean = {
    notifyObservers
    if (hand.playerOneHand.isEmpty == true)
      return true
    end if
    return false
  }

  def showCards(): Unit = {
    hand.showYourCards()
    notifyObservers
  }

  def showTable(): Unit = {
    table.showPlacedCardsOnTable()
    notifyObservers
  }

}
