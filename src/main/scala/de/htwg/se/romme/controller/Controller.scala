package de.htwg.se.romme
package controller

import model.{Card, Deck, Player, PlayerHands}
import _root_.de.htwg.se.romme.util.Observable
//import util.Observer

//class Controller(var card: Cards) extends Observable {
//notifyObservers
//}

case class Controller() extends Observable {
  val deck = new Deck() // Deck-Instanz erstellt
  val hand = new PlayerHands()

  def gameStart(): Unit = {
    deck.createNewDeck() // neues Deck erstellen
    hand.draw13Cards(deck)
    var tmp = 0
    for (tmp <- 0 to 12)
      print(
        hand.playerOneHand(tmp).getCardName()
      ) // Gebe mir alle gezogenen Karten aus
    println()
  }

  def pickUpACard(): Unit = {
    hand.playerOneHand.addOne(deck.drawFromDeck())
    for (tmp <- 0 to hand.playerOneHand.size - 1)
      print(
        hand.playerOneHand(tmp).getCardName()
      ) // Gebe mir alle gezogenen Karten aus
    println()
  }

  def dropASingleCard(index: Integer): Unit = {
    hand.playerOneHand.remove(index)
    for (tmp <- 0 to hand.playerOneHand.size - 1)
      print(
        hand.playerOneHand(tmp).getCardName()
      )
    println()
  }

  def dropASpecificCard(index: Integer): Unit = {
    hand.playerOneHand.remove(index)
    for (tmp <- 0 to hand.playerOneHand.size - 1)
      print(
        hand.playerOneHand(tmp).getCardName()
      )
    println()
  }
}
