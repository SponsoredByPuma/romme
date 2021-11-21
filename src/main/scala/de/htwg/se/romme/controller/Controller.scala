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

  def dropASpecificCard(index: Integer): Unit = {
    hand.dropASingleCard(index)
    println()
    notifyObservers
  }

  def dropMultipleCards(list: ListBuffer[Integer], dec: Integer) : Unit = {
    if(hand.dropCardsOnTable(list, dec) == true)
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
    hand.sortMyCards()
  }
  

  def victory(): Boolean = {
    if (hand.playerOneHand.isEmpty == true)
      notifyObservers
      return true
    else
      notifyObservers
      return false
    end if
  }

  def showCards(): Boolean = {
    hand.showYourCards()
    notifyObservers
    return true
  }

  def showTable(): Boolean = {
    table.showPlacedCardsOnTable()
    notifyObservers
    return true
  }

}
