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

  def dropCardsOnTable(index : ListBuffer[Integer]): Boolean = {
    var droppingCards: ListBuffer[Card] = new ListBuffer()
    if (outside == false)
      var sum = 0
      for(counter <- 0 to index.size - 1)
        droppingCards.addOne(playerOneHand(index(counter))) // adds the element of your hand at the index
        sum += playerOneHand(index(counter)).getValue() // counts the Value of your Cards
      if (sum < 4) // if its less than 40 you are not allowed to drop your Cards
        return false // returns false so you know that it didnt work out
      end if
      table.placeCardsOnTable(droppingCards)
      //outside = true
      return true
    end if
    return false
  }

  def showYourCards(): Unit = {
    for (tmp <- 0 to playerOneHand.size - 1)
      print(
        playerOneHand(tmp).getCardName()
      )
  }
}
