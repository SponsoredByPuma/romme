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

  private val undoManager = new UndoManager

  def gameStart(): Unit = {
    game = game.gameStart()
    notifyObservers
  }

  def pickUpGraveYard(player1Turn: Boolean): Unit = {
    game = game.pickUpGraveYard(player1Turn)
    notifyObservers
  }

  def pickUpACard(player1Turn: Boolean): Unit = {
    game = game.pickUpACard(player1Turn)
    undoManager.doStep(new GameCommand(game, this))
    notifyObservers
  }

  def dropASpecificCard(index: Integer, player1Turn: Boolean): Unit = {
    game = game.dropASpecificCard(index, player1Turn)
    notifyObservers
  }

  def takeJoker(
      idxlist: Integer,
      idxCard: Integer,
      player1Turn: Boolean
  ): Unit = {
    game = game.takeJoker(idxlist, idxCard, player1Turn)
    notifyObservers
  }

  def dropMultipleCards(
      list: ListBuffer[Integer],
      dec: Integer,
      player1Turn: Boolean
  ): Unit = {
    game = game.dropMultipleCards(list, dec, player1Turn)
    notifyObservers
  }

  def sortPlayersCards(player1Turn: Boolean): Unit = {
    game = game.sortPlayersCards(player1Turn)
    notifyObservers
  }

  def victory(player1Turn: Boolean): Boolean = {
    notifyObservers
    game.victory(player1Turn)
  }

  def showCards(player1Turn: Boolean): String = {
    notifyObservers
    if(player1Turn)
      println("PLAYER 1: ")
    else
      println("PLAYER 2: ")
    end if
    print(game.showCards(player1Turn))
    game.showCards(player1Turn)

  }

  def showTable(player1Turn: Boolean): String = {
    notifyObservers
    print(game.showTable)
    game.showTable
  }

  def undo: Unit = {
    undoManager.undoStep
    print(game.deck.deckList.size)
    notifyObservers
  }
  def redo: Unit = {
    undoManager.redoStep
    print(game.deck.deckList.size)
    notifyObservers
  }

  def addCard(
      idxCard: Integer,
      idxlist: Integer,
      player1Turn: Boolean
  ): Unit = {
    notifyObservers
    game = game.addCard(idxCard, idxlist, player1Turn)
  }

}
