package de.htwg.se.romme
package controller

import model.{Card, Deck, Player, PlayerHands, Table, State, Game}
import _root_.de.htwg.se.romme.util.Observable
import de.htwg.se.romme.util.UndoManager
import scala.io.StdIn.readLine
import util.Observable
import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.StateContext

class Controller(var game: Game) extends Observable {
  val deck = new Deck() // Deck-Instanz erstellt
  val table = new Table()
  val hand = new PlayerHands(table) // var hand = new PlayerHands(table)
  private val undoManager = new UndoManager
  /*
  val hand2 = new PlayerHands(table)
  deck.createNewDeck()
  val player1 = new Player("Test",hand,deck,table)
  val player2 = new Player("Test2",hand2,deck,table)
   */

  def gameStart(): Unit = {
    game = game.gameStart()
    notifyObservers
  }

  def pickUpGraveYard(): Unit = {
    game = game.pickUpGraveYard()
    notifyObservers
  }

  def pickUpACard(): Unit = {
    game = game.pickUpACard()
    notifyObservers
  }

  def dropASpecificCard(index: Integer): Unit = {
    game = game.dropASpecificCard(index)
    notifyObservers
  }

  def takeJoker(idxlist: Integer, idxCard: Integer): Unit = {
    /*
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
     */
    game = game.takeJoker(idxlist, idxCard)
    notifyObservers
  }

  def dropMultipleCards(list: ListBuffer[Integer], dec: Integer): Unit = {
    /*
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
     */
    game = game.dropMultipleCards(list, dec)
    notifyObservers
  }

  def sortPlayersCards(): Unit = {
    game = game.sortPlayersCards()
    notifyObservers
  }

  def victory(): Boolean = {
    notifyObservers
    game.victory()
  }

  def showCards(): Boolean = {
    notifyObservers
    game.showCards()

  }

  def showTable(): Boolean = {
    notifyObservers
    game.showTable()

  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }
  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }

}
