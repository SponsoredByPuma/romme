package de.htwg.se.romme
package controller.controllerComponent.controllerBaseImpl

import model.modelComponent.GameInterface
import model.modelComponent.gameBaseImpl._
import controller.controllerComponent.ControllerInterface
import _root_.de.htwg.se.romme.util.Observable
import de.htwg.se.romme.util.UndoManager
import scala.io.StdIn.readLine
import util.Observable
import scala.collection.mutable.ListBuffer

import scala.swing.Publisher

class Controller(var game: GameInterface) extends ControllerInterface with Publisher{

  private val undoManager = new UndoManager

  var player1Turn: Boolean = true

  def gameStart: Unit = {
    game = game.gameStart 
    //notifyObservers
    publish(new showPlayerTable)
  }

  def checkForJoker(list: ListBuffer[Integer]): ListBuffer[Integer] = {
    var tmpList: ListBuffer[Integer] = ListBuffer()
    var tmpCards: ListBuffer[Card] = ListBuffer()
    var returnValues: ListBuffer[Integer] = ListBuffer()
    if(player1Turn)
      for (x <- 0 to (list.size - 1))
        tmpCards.addOne(game.player.hands.playerOneHand(list(x))) // die Stellen der Karten, die ich ablegen möchte
      
      for (x <- 0 to tmpCards.size - 1) 
        if(tmpCards(x).placeInList.get == 15)
          returnValues.addOne(list(x))
      returnValues
    else
      for (x <- 0 to (list.size - 1))
      tmpCards.addOne(game.player2.hands.playerOneHand(list(x)))
    
      for (x <- 0 to tmpCards.size - 1)
        if(tmpCards(x).placeInList.get == 15)
          returnValues.addOne(list(x))
      returnValues
    end if
  }

  def replaceCardOrder(stelle: ListBuffer[Integer], values: ListBuffer[String]) : Unit = {
      game = game.replaceCardOrder(stelle,values,player1Turn)
  }

  def replaceCardSuit(stelle: ListBuffer[Integer], values: ListBuffer[String]) : Unit = {
    game = game.replaceCardSuit(stelle,values,player1Turn)
  }

  def switch: Unit = {
    player1Turn = !player1Turn
    publish(new showPlayerCards)
  }

  def playersTurn: Boolean = {
    this.player1Turn
  }

  def pickUpGraveYard: Unit = {
    game = game.pickUpGraveYard(player1Turn)
   //notifyObservers
    publish(new showPlayerCards)
    publish(new showPlayerTable)
  }

  def pickUpACard: Unit = {
    game = game.pickUpACard(player1Turn)
    undoManager.doStep(new GameCommand(game, this))
    //notifyObservers
    publish(new showPlayerCards)
  }

  def dropASpecificCard(index: Integer): Unit = {
    game = game.dropASpecificCard(index, player1Turn)
    //notifyObservers
    publish(new showPlayerCards)
    publish(new showPlayerTable)
  }

  def takeJoker(
      idxlist: Integer,
      idxCard: Integer): Unit = {
    game = game.takeJoker(idxlist, idxCard, player1Turn)
    //notifyObservers
    publish(new showPlayerCards)
    publish(new showPlayerTable)
  }

  def dropMultipleCards(
      list: ListBuffer[Integer],
      dec: Integer,
      hasJoker:Boolean
  ): Unit = {
    game = game.dropMultipleCards(list, dec, player1Turn,hasJoker)
    //notifyObservers
    publish(new showPlayerCards)
    publish(new showPlayerTable)
  }

  def sortPlayersCards: Unit = {
    game = game.sortPlayersCards(player1Turn)
    //notifyObservers
    publish(new showPlayerCards)
  }

  def victory: Boolean = {
    //notifyObservers
    game.victory(player1Turn)
  }

  def showCards: String = {
    var s = ""
    //notifyObservers
    if(player1Turn)
      s = "PLAYER 1: "
    else
      s = "PLAYER 2: "
    end if
    s = s + game.showCards(player1Turn)
    s
  }

  def getCards: ListBuffer[Card] = {
    if(player1Turn)
      game.player.hands.playerOneHand
    else
      game.player2.hands.playerOneHand
    end if
  }

  def getCardsTable: ListBuffer[ListBuffer[Card]] = {
    game.table.droppedCardsList
  }

  def getGraveyardCard: Card = game.table.graveYard

  def showTable: String = {
    //notifyObservers
    game.showTable
  }

  def undo: Unit = {
    undoManager.undoStep
    print(game.deck.deckList.size)
    //notifyObservers
  }
  def redo: Unit = {
    undoManager.redoStep
    print(game.deck.deckList.size)
    //notifyObservers
  }

  def addCard(
      idxCard: Integer,
      idxlist: Integer
  ): Unit = {
    //notifyObservers
    game = game.addCard(idxCard, idxlist, player1Turn)
    publish(new showPlayerCards)
    publish(new showPlayerTable)
  }

}
