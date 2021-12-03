package de.htwg.se.romme
package model

import scala.collection.mutable.ListBuffer

case class Game(table: Table,var player: Player, var player2: Player, deck: Deck):

    def set(table: Table,player: Player, player2: Player,deck: Deck): Game = copy(table,player,player2,deck)

    def gameStart(): Game = {
        deck.createNewDeck()
        player.hands.draw13Cards(deck)
        player2.hands.draw13Cards(deck)
        copy(table,player, player2, deck)
    }

    def pickUpGraveYard(player1Turn: Boolean) : Game = {
      if(player1Turn)
        player = player.pickUpGraveYard
      else
        player2 = player2.pickUpGraveYard
      end if
      copy(table,player, player2, deck)
    }

    def pickUpACard(player1Turn: Boolean): Game = {
      if(player1Turn)
        player = player.pickUpACard(deck)
      else
        player2 = player2.pickUpACard(deck)
      end if
      copy(table,player, player2, deck)
    }

    def dropASpecificCard(index: Integer, player1Turn: Boolean) : Game = {
      if(player1Turn)
        player = player.dropASpecificCard(index)
      else
        player2 = player2.dropASpecificCard(index)
      end if
      copy(table,player, player2, deck)
    }

    def addCard(idxCard: Integer, idxlist: Integer, player1Turn: Boolean): Game = {
      if(player1Turn)
        player = player.addCard(idxCard,idxlist)
      else
        player2 = player2.addCard(idxCard,idxlist)
      end if
      copy(table,player, player2, deck)
    }  

    def takeJoker(idxlist: Integer, idxCard: Integer, player1Turn: Boolean) : Game = {
        if(player1Turn)
          player = player.takeJoker(idxlist,idxCard)
        else
          player2 = player2.takeJoker(idxlist,idxCard)
        end if
        copy(table,player, player2, deck)
    }

    def dropMultipleCards(list: ListBuffer[Integer], dec: Integer, player1Turn: Boolean) : Game = {
      if(player1Turn)
        player = player.dropMultipleCards(list,dec)
      else
        player2 = player2.dropMultipleCards(list,dec)
      end if
      copy(table,player, player2, deck)
    }

    def sortPlayersCards(player1Turn: Boolean) : Game = {
        if(player1Turn)
          player.sortPlayersCards
        else
          player2.sortPlayersCards
        end if
        copy(table,player, player2, deck)
    }

    def victory(player1Turn: Boolean) : Boolean = {
      if(player1Turn)
        player.victory
      else
        player2.victory
      end if
    }

    def showCards(player1Turn: Boolean) : String = {
      if(player1Turn)
        player.showCards
      else
        player2.showCards
      end if
    }

    def showTable : String = player.showTable

    
