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
    undoManager.doStep(new GameCommand(game, this))
    notifyObservers
  }

  def dropASpecificCard(index: Integer): Unit = {
    game = game.dropASpecificCard(index)
    notifyObservers
  }

  def takeJoker(idxlist: Integer, idxCard: Integer): Unit = {
    game = game.takeJoker(idxlist, idxCard)
    notifyObservers
  }

  def dropMultipleCards(list: ListBuffer[Integer], dec: Integer): Unit = {
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

  def showCards(): String = {
    notifyObservers
    print(game.showCards())
    game.showCards()

  }

  def showTable(): String = {
    notifyObservers
    print(game.showTable())
    game.showTable()
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

}
