package de.htwg.se.romme
package model

import scala.collection.mutable.ListBuffer

case class Player(name: String, hands: PlayerHands, table: Table) {
  def getName: String = name

  def pickUpGraveYard: Player = {
    val d = table.grabGraveYard()
    hands.playerOneHand.addOne(d.get)
    copy(name, hands, table)
  }

  def pickUpACard(deck: Deck): Player = {
    val d = deck.drawFromDeck()
    hands.playerOneHand.addOne(d)
    copy(name, hands, table)
  }

  def dropASpecificCard(index: Integer): Player = {
    hands.dropASingleCard(index)
    copy(name, hands, table)
  }

  def addCard(idxCard: Integer, idxlist: Integer): Player = {
        var tmpTableList: ListBuffer[Card] = ListBuffer()
        tmpTableList.addAll(table.droppedCardsList(idxlist))
        

        if ((tmpTableList(0).placeInList.get != tmpTableList(1).placeInList.get && !(tmpTableList(0).getSuit.equals("Joker")) && !(tmpTableList(1).getSuit.equals("Joker"))) || tmpTableList(0).getSuit.equals("Joker") || tmpTableList(1).getSuit.equals("Joker")) // nach order sortiert
            var card: Card = hands.playerOneHand(idxCard)
            tmpTableList.addOne(card)
            var list: ListBuffer[Card] = ListBuffer()
            list = tmpTableList.sortBy(_.placeInList)
            list = lookForGaps(list)
            if(list.isEmpty)
                print("error list has gaps !")
                return this
            end if
            hands.playerOneHand.remove(idxCard)
            table.droppedCardsList.insert(idxlist,list)
            table.droppedCardsList.remove(idxlist + 1)
            return copy(name, hands, table)
        else // nach Suit gelegt
            if(tmpTableList.size == 4) // bei 4 karten kann man nichts mehr anlegen
                print("error its already full")
                return this
            end if
            var storeRanks: ListBuffer[Integer] = ListBuffer()
            tmpTableList.addOne(hands.playerOneHand(idxCard))
            var boo: Boolean = false
            for(c <- tmpTableList)
                storeRanks.addOne(c.placeInList.get)
                if (c.getCardName.equals("Joker",""))
                    boo = true
                end if
            
            if(boo) // es gibt Joker
                if(storeRanks.distinct.size > 2)
                    print("Fehler bei storeRanks mit Joker")
                    return this
                end if
            else
                if(storeRanks.distinct.size > 1)
                    print("Fehler bei storeRanks ohne Joker")
                    return this
                end if
            end if
            
            hands.playerOneHand.remove(idxCard)
            table.droppedCardsList.insert(idxlist,tmpTableList)
            table.droppedCardsList.remove(idxlist + 1)
            return copy(name, hands, table)
        end if
    }

  def lookForGaps(list: ListBuffer[Card]): ListBuffer[Card] = {
    var lowestCard = lookForLowestCard(list)
    if(lowestCard == 0 && checkForAce(list)) // if there is an ace and a two in the order the ace and two need to be flexible
      var splitter = 0
      while(splitter == list(splitter).placeInList.get) // solange die Reihenfolge noch passt erhöhe den counter
        splitter = splitter + 1
      var secondList: ListBuffer[Card] = ListBuffer()
      for (x <- splitter to list.size - 1) // adde alle Element nach der Lücke hinzu
        secondList.addOne(list(splitter))
        splitter = splitter + 1
      var newList: ListBuffer[Card] = ListBuffer()
      newList.addAll(secondList) // füge erst die Bube,Dame, König, Ass hinzu

      var thirdList: ListBuffer[Card] = ListBuffer() 
      thirdList = list.filter(_.placeInList.get < splitter)
      newList.addAll(thirdList) // danach die 2,3,4,5...

      var next = newList(0).placeInList.get

      for(x <- 0 until newList.size - 1)
        next = next + 1
        if(newList(x).placeInList.get == 12)
          next = 0
        end if
        if (next != newList(x + 1).placeInList.get)
          return newList.empty // return false
        end if
      newList // return true
    else
      var next = list(0).placeInList.get
      for (x <- 0 until (list.size - 1)) // until, since the last card has no next 
        next = next + 1 // increase next for 1
        if(list(x + 1).placeInList.get != next)
          return list.empty // return false
        end if
      list // return true
    end if
  }

  def lookForLowestCard(list: ListBuffer[Card]): Integer = {
    var lowestCard = list(0).placeInList.get
    for (x <- 0 to list.size - 1)
      if (list(x).placeInList.get < lowestCard)
        lowestCard = list(x).placeInList.get
      end if
    lowestCard
  }

  def checkForAce(list: ListBuffer[Card]): Boolean = {
    for(x <- 0 to list.size - 1)
      if (list(x).placeInList.get == 12)
        return true
      end if
    false
  }

  def takeJoker(idxlist: Integer, idxCard: Integer) : Player = {
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
            if (hands.playerOneHand(idxCard).getSuit.equals(tmpTableList(storeJokerPlace(x)).getSuit) && hands.playerOneHand(idxCard).getValue == tmpTableList(storeNormalCards(0)).getValue) // schaue ob deine Card auch der gewünschte Suit hat
                tmpTableList.insert(storeJokerPlace(x), hands.playerOneHand(idxCard)) // füge deine Karte ein
                tmpTableList.remove(storeJokerPlace(x) + 1) // remove den Joker
                hands.playerOneHand.remove(idxCard) // remove deine Karte von der Hand
                hands.playerOneHand.addOne(Card(4,0)) // gebe dir einen Joker auf die hand
                table.droppedCardsList.insert(idxlist,tmpTableList)// füge die neue Liste auf dem Tisch ein
                table.droppedCardsList.remove(idxlist + 1) // lösche die Alte Liste
                println("im Strategy Suit 0 ")
                return copy(name,hands,table)
            end if
    else // Strategy 1 Order
        for (x <- 0 to storeJokerPlace.size - 1)
            if(hands.playerOneHand(idxCard).getValue == tmpTableList(storeJokerPlace(x)).getValue && hands.playerOneHand(idxCard).getSuit.equals(tmpTableList(storeNormalCards(0)).getSuit)) // schaue ob deine Card auch der gewünschte Value hat
                tmpTableList.insert(storeJokerPlace(x), hands.playerOneHand(idxCard)) // füge deine Karte ein
                tmpTableList.remove(storeJokerPlace(x) + 1) // remove den Joker
                hands.playerOneHand.remove(idxCard) // remove deine Karte von der Hand
                hands.playerOneHand.addOne(Card(4,0)) // gebe dir einen Joker auf die hand
                table.droppedCardsList.insert(idxlist,tmpTableList) // füge die neue Liste auf dem Tisch ein
                table.droppedCardsList.remove(idxlist + 1) // lösche die Alte Liste
                println("im Strategy Order 1 ")
                return copy(name,hands,table)
            end if
    end if
    copy(name,hands,table)
  }

  def dropMultipleCards(list: ListBuffer[Integer], dec: Integer) : Player = {
    if(hands.dropCardsOnTable(list, dec) == true)
      list.sorted // sortiere die Liste
      for (counter <- 0 to list.size - 1) // gehe die Liste durch
      // falls die Zahl 0 < 12 ist müssen die restlichen Cards um 1 verringert werden, da bei remove eins weggenommen wird
        if (list(counter) < hands.playerOneHand.size - 1)
            for(counter <- counter + 1 to list.size - 1) // go through the next inputs
                list(counter) = list(counter) - 1 // decrement the next input for one
        end if
        hands.playerOneHand.remove(list(counter)) // remove the Card
    end if
    copy(name,hands,table)
  }

  def sortPlayersCards : Player = {
        hands.sortMyCards()
        copy(name,hands,table)
    }

    def victory: Boolean = {
        if (hands.playerOneHand.isEmpty == true)
            true 
        else
            false
        end if
    }

    def showCards: String = hands.showYourCards()

    def showTable: String = table.showPlacedCardsOnTable()

}

