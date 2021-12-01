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
    undoManager.doStep(new GameCommand(game, this))
    notifyObservers
  }

  def pickUpGraveYard(): Unit = {
    game = game.pickUpGraveYard()
    undoManager.doStep(new GameCommand(game, this))
    notifyObservers
  }

  def pickUpACard(): Unit = {
    game = game.pickUpACard()
    undoManager.doStep(new GameCommand(game, this))
    notifyObservers
  }

  def dropASpecificCard(index: Integer): Unit = {
    game = game.dropASpecificCard(index)
    undoManager.doStep(new GameCommand(game, this))
    notifyObservers
  }

  def takeJoker(idxlist: Integer, idxCard: Integer): Unit = {
    game = game.takeJoker(idxlist, idxCard)
    undoManager.doStep(new GameCommand(game, this))
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
    undoManager.doStep(new GameCommand(game, this))
    notifyObservers
  }

  def sortPlayersCards(): Unit = {
    game = game.sortPlayersCards()
    undoManager.doStep(new GameCommand(game, this))
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
