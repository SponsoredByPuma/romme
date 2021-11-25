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

  def takeJoker(idxlist: Integer, idxCard: Integer) : Unit = {
    var tmpTableList: ListBuffer[Card] = ListBuffer()
    tmpTableList.addAll(table.droppedCardsList(idxlist))
    var tmpSuit: ListBuffer[String] = ListBuffer()
    var tmpRank: ListBuffer[Integer] = ListBuffer()
    var storeJokerPlace: ListBuffer[Integer] = ListBuffer()
    var storeNormalCards: ListBuffer[Integer] = ListBuffer()
    for (x <- 0 to tmpTableList.size - 1)
      if (tmpTableList(x).getCardName.equals("Joker",""))
        storeJokerPlace.addOne(x)
      end if
      storeNormalCards.addOne(x)
      tmpSuit.addOne(tmpTableList(x).getSuit)
      tmpRank.addOne(tmpTableList(x).getValue)
    
    if (tmpSuit.distinct.size > 1 + storeJokerPlace.size) // Strategy 0 Suit
      for(x <- 0 to storeJokerPlace.size - 1)
        if (hand.playerOneHand(idxCard).getSuit.equals(tmpTableList(storeJokerPlace(x)).getSuit) && hand.playerOneHand(idxCard).getValue == tmpTableList(storeNormalCards(0)).getValue) // schaue ob deine Card auch der gewünschte Suit hat
          tmpTableList.insert(storeJokerPlace(x), hand.playerOneHand(idxCard)) // füge deine Karte ein
          tmpTableList.remove(storeJokerPlace(x) + 1) // remove den Joker
          hand.playerOneHand.remove(idxCard) // remove deine Karte von der Hand
          hand.playerOneHand.addOne(Card(4,0)) // gebe dir einen Joker auf die hand
          table.droppedCardsList.insert(idxlist,tmpTableList)// füge die neue Liste auf dem Tisch ein
          table.droppedCardsList.remove(idxlist + 1) // lösche die Alte Liste
        end if
      
    else // Strategy 1 Order
      for (x <- 0 to storeJokerPlace.size - 1)
        if(hand.playerOneHand(idxCard).getValue == tmpTableList(storeJokerPlace(x)).getValue && hand.playerOneHand(idxCard).getSuit.equals(tmpTableList(storeNormalCards(0)).getSuit)) // schaue ob deine Card auch der gewünschte Value hat
          tmpTableList.insert(storeJokerPlace(x), hand.playerOneHand(idxCard)) // füge deine Karte ein
          tmpTableList.remove(storeJokerPlace(x) + 1) // remove den Joker
          hand.playerOneHand.remove(idxCard) // remove deine Karte von der Hand
          hand.playerOneHand.addOne(Card(4,0)) // gebe dir einen Joker auf die hand
          table.droppedCardsList.insert(idxlist,tmpTableList) // füge die neue Liste auf dem Tisch ein
          table.droppedCardsList.remove(idxlist + 1) // lösche die Alte Liste
        end if

    end if
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
