package de.htwg.se.romme

package controller.controllerComponent

import _root_.de.htwg.se.romme.util.Observable
import de.htwg.se.romme.util.UndoManager
import scala.io.StdIn.readLine
import util.Observable
import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.modelComponent.GameInterface
import de.htwg.se.romme.model.modelComponent.gameBaseImpl.StateContext
import de.htwg.se.romme.model.modelComponent.gameBaseImpl.Card

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  var game: GameInterface
  var player1Turn: Boolean
  def gameStart: Unit
  def checkForJoker(list: ListBuffer[Integer]): ListBuffer[Integer]
  def replaceCardOrder(
      stelle: ListBuffer[Integer],
      values: ListBuffer[String]
  ): Unit
  def replaceCardSuit(
      stelle: ListBuffer[Integer],
      values: ListBuffer[String]
  ): Unit
  def switch: Unit
  def playersTurn: Boolean
  def pickUpGraveYard: Unit
  def pickUpACard: Unit
  def dropASpecificCard(index: Integer): Unit
  def takeJoker(idxlist: Integer, idxCard: Integer): Unit
  def dropMultipleCards(
      list: ListBuffer[Integer],
      dec: Integer,
      hasJoker: Boolean
  ): Unit
  def sortPlayersCards: Unit
  def victory: Boolean
  def showCards: String
  def getCards: ListBuffer[Card]
  def getCardsTable: ListBuffer[ListBuffer[Card]]
  def getGraveyardCard: Card
  def showTable: String
  def undo: Unit
  def redo: Unit
  def addCard(idxCard: Integer, idxlist: Integer): Unit
}
