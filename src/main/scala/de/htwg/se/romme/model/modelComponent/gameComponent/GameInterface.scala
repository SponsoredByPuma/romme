package de.htwg.se.romme.model.modelComponent.gameComponent

import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Card
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Table
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Deck
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Player
import scala.collection.mutable.ListBuffer

trait GameInterface:

    val table: Table
    var player: Player
    var player2: Player
    val deck: Deck

    def set(table: Table, player: Player, player2: Player, deck: Deck): GameInterface
    def gameStart: GameInterface
    def pickUpGraveYard(player1Turn: Boolean) : GameInterface
    def pickUpACard(player1Turn: Boolean) : GameInterface
    def replaceCardOrder(stelle: ListBuffer[Integer], values: ListBuffer[String], player1Turn: Boolean) : GameInterface
    def replaceCardSuit(stelle: ListBuffer[Integer], values: ListBuffer[String], player1Turn: Boolean) : GameInterface
    def dropASpecificCard(index: Integer, player1Turn: Boolean) : GameInterface
    def addCard(idxCard: Integer, idxlist: Integer, player1Turn: Boolean) : GameInterface
    def takeJoker(idxlist: Integer, idxCard: Integer, player1Turn: Boolean) : GameInterface
    def dropMultipleCards(list: ListBuffer[Integer], dec: Integer, player1Turn: Boolean, hasJoker: Boolean) : GameInterface
    def sortPlayersCards(player1Turn: Boolean) : GameInterface
    def victory(player1Turn: Boolean) : Boolean
    def showCards(player1Turn: Boolean) : String
    def showTable: String
    
