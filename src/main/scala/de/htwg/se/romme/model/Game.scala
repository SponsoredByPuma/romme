package de.htwg.se.romme
package model

import scala.collection.mutable.ListBuffer

case class Game(table: Table, hand: PlayerHands, deck: Deck):

    def set(table: Table,hand: PlayerHands,deck: Deck): Game = copy(table,hand,deck)

    def gameStart(): Game = {
        deck.createNewDeck()
        hand.draw13Cards(deck)
        return copy(table,hand, deck)
    }

    def pickUpGraveYard() : Game = {
        val d = table.grabGraveYard()
        hand.playerOneHand.addOne(d.get)
        return copy(table,hand,deck)
    }

    def pickUpACard(): Game = {
        val d = deck.drawFromDeck()
        hand.playerOneHand.addOne(d)
        return copy(table,hand,deck)
    }

    def dropASpecificCard(index: Integer) : Game = {
        hand.dropASingleCard(index)
        return copy(table,hand,deck)
    }

    def takeJoker(idxlist: Integer, idxCard: Integer) : Game = {
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
                    println("im Strategy Suit 0 ")
                    return copy(table,hand,deck)
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
                    println("im Strategy Order 1 ")
                    return copy(table,hand,deck)
                end if
        end if
        return copy(table,hand,deck)
    }

    def dropMultipleCards(list: ListBuffer[Integer], dec: Integer) : Game = {
      if(hand.dropCardsOnTable(list, dec) == true)
        list.sorted // sortiere die Liste
        for (counter <- 0 to list.size - 1) // gehe die Liste durch
        // falls die Zahl 0 < 12 ist müssen die restlichen Cards um 1 verringert werden, da bei remove eins weggenommen wird
            if (list(counter) < hand.playerOneHand.size - 1)
                for(counter <- counter + 1 to list.size - 1) // go through the next inputs
                    list(counter) = list(counter) - 1 // decrement the next input for one
            end if
            hand.playerOneHand.remove(list(counter)) // remove the Card
      end if
      return copy(table,hand,deck)
    }

    def sortPlayersCards() : Game = {
        hand.sortMyCards()
        return copy(table,hand,deck)
    }

    def victory(): Boolean = {
        if (hand.playerOneHand.isEmpty == true)
            return true 
        else
            return false
        end if
    }

    def showCards(): Boolean = {
        hand.showYourCards()
        true
    }

    def showTable(): Boolean = {
        table.showPlacedCardsOnTable()
        true
    }
