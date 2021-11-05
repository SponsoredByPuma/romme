package de.htwg.se.romme
package controller

import model.{Card, Deck, Player, PlayerHands}
import _root_.de.htwg.se.romme.util.Observable
import scala.io.StdIn.readLine
import util.Observer

class Controller() extends Observable {
  val deck = new Deck() // Deck-Instanz erstellt
  val hand = new PlayerHands()

  def gameStart(): Unit = {
    deck.createNewDeck() // neues Deck erstellen
    hand.draw13Cards(deck)
    var tmp = 0
    //for (tmp <- 0 to 12)
    //  print(
    //    hand.playerOneHand(tmp).getCardName()
    //  ) // Gebe mir alle gezogenen Karten aus
    println()
    //notifyObservers
  }

  def pickUpACard(): Unit = {
    hand.playerOneHand.addOne(deck.drawFromDeck())
    //for (tmp <- 0 to hand.playerOneHand.size - 1)
    //  print(
    //    hand.playerOneHand(tmp).getCardName()
    //  ) // Gebe mir alle gezogenen Karten aus
    //println()
    //notifyObservers
  }

  def dropASpecificCard(): Unit = {
    println("Which card would you like to drop ?")
    var index = readLine()
    hand.playerOneHand.remove(index.toInt)
    //for (tmp <- 0 to hand.playerOneHand.size - 1)
    //  print(
    //    hand.playerOneHand(tmp).getCardName()
    //  )
    println()
    //notifyObservers
  }

  def victory(): Boolean = {
    if (hand.playerOneHand.isEmpty == true)
      //notifyObservers
      return true
    end if
    //notifyObservers
    return false
  }

  def showCards(): Unit = {
    for (tmp <- 0 to hand.playerOneHand.size - 1)
      print(
        hand.playerOneHand(tmp).getCardName()
      )
      //notifyObservers
  }

}
