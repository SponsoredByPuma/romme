package de.htwg.se.romme
package model

import scala.collection.mutable.ListBuffer
import scala.util.Try
import scala.util.Failure
import scala.util.Success
//import de.htwg.se.romme.model.Card

class Table() {

  var droppedCardsList: ListBuffer[ListBuffer[Card]] = new ListBuffer()

  var graveYard =
    Card(5, 0) // the last dropped card
  
  def replaceGraveYard(card: Card): Unit = {
    graveYard = card
  }

  def placeCardsOnTable(cards: ListBuffer[Card]): Unit = {
    droppedCardsList.append(cards)
  }

  def showPlacedCardsOnTable(): Unit = {
    println("GraveYard: " + this.graveYard.getCardName)
    // for the size of the dropped Cards
    for (tmp <- 0 to droppedCardsList.size - 1) {
      println()
      // for each Card in the List
      for (tmp2 <- 0 to droppedCardsList(tmp).size - 1) {
        print(droppedCardsList(tmp)(tmp2).getCardName)
      }
    }
  }

  def grabGraveYard(): Option[Card] = {
    if(graveYard.getCardName.equals("",""))
      None
    end if
    val returnCard = graveYard // safe the graveYard Card
    graveYard = Card(5, 13) // delete the graveYard
    return Some(returnCard) // return the Card
  }

}
