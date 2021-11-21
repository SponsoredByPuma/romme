package de.htwg.se.romme

package model

import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.Deck
import de.htwg.se.romme.model.Table
import de.htwg.se.romme.model.Drops

class PlayerHands(table: Table) {
  var playerOneHand: ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
  var outside: Boolean = false

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

    for(counter <- 0 to (index.size - 1))
      droppingCards.addOne(playerOneHand(index(counter)))// adds the element of your hand at the index
    if(outside == false)
      if (drop.execute(droppingCards,dec) < 40)
        println("LOOOOOOOOOOOOSER")
        return false
      end if
      table.placeCardsOnTable(droppingCards)
      outside = true
      return true
    else
      println("You are outside !")
      if(drop.execute(droppingCards, dec) == 0)
        println("RIP something went wrong")
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
