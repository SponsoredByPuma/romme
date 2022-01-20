package de.htwg.se.romme.controller.controllerComponent.controllerMockImpl

import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.modelComponent.gameComponent.GameInterface
import de.htwg.se.romme.controller.controllerComponent.ControllerInterface
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Card

class Controller(var game: GameInterface) extends ControllerInterface {

  var player1Turn = false

  def gameStart: Unit = {}
  def checkForJoker(list: ListBuffer[Integer]): ListBuffer[Integer] = {
    var t1: ListBuffer[Integer] = ListBuffer()
    t1
  }
  def replaceCardOrder(
      stelle: ListBuffer[Integer],
      values: ListBuffer[String]
  ): Unit = {}
  def replaceCardSuit(
      stelle: ListBuffer[Integer],
      values: ListBuffer[String]
  ): Unit = {}
  def switch: Unit = {}
  def playersTurn: Boolean = false
  def pickUpGraveYard: Unit = {}
  def pickUpACard: Unit = {}
  def dropASpecificCard(index: Integer): Unit = {}
  def takeJoker(idxlist: Integer, idxCard: Integer): Unit = {}
  def dropMultipleCards(
      list: ListBuffer[Integer],
      dec: Integer,
      hasJoker: Boolean
  ): Unit = {}
  def sortPlayersCards: Unit = {}
  def victory: Boolean = false
  def showCards: String = ""
  def getCards: ListBuffer[Card] = {
    var t1: ListBuffer[Card] = ListBuffer()
    t1
  }
  def getCardsTable: ListBuffer[ListBuffer[Card]] = {
    var t1: ListBuffer[ListBuffer[Card]] = ListBuffer()
    t1
  }
  def getGraveyardCard: Card = {
    var t1 = Card(0, 0)
    t1
  }
  def showTable: String = { "" }
  def undo: Unit = {}
  def redo: Unit = {}
  def addCard(idxCard: Integer, idxlist: Integer): Unit = {}
  def load: Unit = {}
  def save: Unit = {}

}
