package de.htwg.se.romme

package model

import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.Deck
import de.htwg.se.romme.model.Table

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
    var heart : ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
    var club : ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
    var diamond : ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
    var spades : ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()
    var joker : ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()

    for (cardIterator <- playerOneHand)
      cardIterator.getSuit match {
        case "Heart" => heart.addOne(cardIterator)
        case "Club" => club.addOne(cardIterator)
        case "Diamond" => diamond.addOne(cardIterator)
        case "Spades" => spades.addOne(cardIterator)
        case "Joker" => joker.addOne(cardIterator)
      }
    // sort all the list by its ranks
    /*
    heart = heart.sortWith(_.rank < rank)
    club = club.sortWith(_.rank < _.rank)
    diamond = diamond.sortWith(_.rank < _.rank)
    spades = spades.sortWith(_.rank < _.rank)s
    joker = joker.sortWith(_.rank < _.rank)
    */


    playerOneHand = playerOneHand.addAll(heart)
    playerOneHand = playerOneHand.addAll(diamond)
    playerOneHand = playerOneHand.addAll(spades)
    playerOneHand = playerOneHand.addAll(club)
    playerOneHand = playerOneHand.addAll(joker)
  }

  def dropCardsOnTable(index : ListBuffer[Integer]): Boolean = {
    var droppingCards: ListBuffer[Card] = new ListBuffer()
    if (outside == false)
      var sum = 0
      for(counter <- 0 to index.size - 1)
        droppingCards.addOne(playerOneHand(index(counter))) // adds the element of your hand at the index
        sum += playerOneHand(index(counter)).getValue // counts the Value of your Cards
      if (sum < 4) // if its less than 40 you are not allowed to drop your Cards
        return false // returns false so you know that it didnt work out
      end if
      table.placeCardsOnTable(droppingCards)
      outside = true
      return true
    else
      println("You are outside !") // just for testing pourpose
      for (counter <- 0 to index.size - 1)
        droppingCards.addOne(playerOneHand(index(counter)))
      table.placeCardsOnTable(droppingCards)
      return true
    end if
    return false
  }

  def showYourCards(): Unit = {
    for (tmp <- 0 to playerOneHand.size - 1)
      print(
        playerOneHand(tmp).getCardName
      )
  }
}
