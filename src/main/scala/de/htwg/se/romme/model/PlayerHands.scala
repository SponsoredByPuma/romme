package de.htwg.se.romme

package model

import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.Deck
import de.htwg.se.romme.model.Table
import de.htwg.se.romme.model.Drops
import de.htwg.se.romme.model.State

class PlayerHands(table: Table) {
  var playerOneHand: ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
 // var outside: Boolean = false
  var outside = StateContext()
  outside.setState(StateOutsideFalse())

  def draw13Cards(d: Deck): Unit = {
    for (counter <- 0 to 12) {
      playerOneHand.addOne(d.drawFromDeck())
    }
  }

  def dropASingleCard(index: Integer): Unit = {
    table.replaceGraveYard(playerOneHand(index))
    playerOneHand.remove(index)
  }

  def sortMyCards(): Unit = {
    var heart: ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
    var club: ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
    var diamond: ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
    var spades: ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
    var joker: ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()

    for (cardIterator <- playerOneHand)
      cardIterator.getSuit match {
        case "Heart"   => heart.addOne(cardIterator)
        case "Club"    => club.addOne(cardIterator)
        case "Diamond" => diamond.addOne(cardIterator)
        case "Spades"  => spades.addOne(cardIterator)
        case "Joker"   => joker.addOne(cardIterator)
      }
    // sort all the list by its ranks

    heart = heart.sortBy(_.placeInList)
    club = club.sortBy(_.placeInList)
    diamond = diamond.sortBy(_.placeInList)
    spades = spades.sortBy(_.placeInList)

    playerOneHand = playerOneHand.empty // empty the playerHand

    playerOneHand = playerOneHand.addAll(heart)
    playerOneHand = playerOneHand.addAll(diamond)
    playerOneHand = playerOneHand.addAll(spades)
    playerOneHand = playerOneHand.addAll(club)
    playerOneHand = playerOneHand.addAll(joker)
  }

  def dropCardsOnTable(index: ListBuffer[Integer], dec: Integer): Boolean = {
    val drop = Drops
    var droppingCards: ListBuffer[Card] = new ListBuffer()
    var sum = 0

    for(counter <- 0 to (index.size - 1))
      droppingCards.addOne(playerOneHand(index(counter)))// adds the element of your hand at the index

    if(outside.getStateB() == false)
      droppingCards = drop.execute(droppingCards,dec)
      if (dec == 0)
        var count = 0
        while(droppingCards(count).getValue.equals(2))
          count = count + 1
        sum = droppingCards.size * droppingCards(count).getValue
      else
        for (card <- droppingCards)
          sum = sum + card.getValue
      end if
      print(sum)
      if (sum < 40)
        println("SCORE IS BELOW 40 F")
        return false
      end if
      table.placeCardsOnTable(droppingCards)
      outside.setState(StateOutSideTrue())
      return true
    else
      droppingCards = drop.execute(droppingCards, dec)
      if(droppingCards.isEmpty)
        println("YOUR SUM IS ZERO BRO")
        return false
      end if
      println("You did it")
      table.placeCardsOnTable(droppingCards)
      return true
    end if
    return true
  }
  
  def showYourCards(): Unit = {
    for (tmp <- 0 to playerOneHand.size - 1)
      print(
        playerOneHand(tmp).getCardName
      )
  }
}
