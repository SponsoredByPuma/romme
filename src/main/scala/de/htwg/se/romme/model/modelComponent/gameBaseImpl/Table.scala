package de.htwg.se.romme.model.modelComponent.gameBaseImpl

import scala.collection.mutable.ListBuffer

class Table() {

  var droppedCardsList: ListBuffer[ListBuffer[Card]] = new ListBuffer()

  var graveYard = Card(5, 0)
  
  def replaceGraveYard(card: Card): Unit = graveYard = card

  def placeCardsOnTable(cards: ListBuffer[Card]): Unit = droppedCardsList.append(cards)

  def showPlacedCardsOnTable(): String = { // hier returne ich den String und printe nicht
    var s: String = ""
    s = "GraveYard: " + this.graveYard.getCardName + "\n"
    // for the size of the dropped Cards
    for (tmp <- 0 to droppedCardsList.size - 1) {
      // for each Card in the List
      s = s + "\n"
      s = s + (tmp + 1)
      for (tmp2 <- 0 to droppedCardsList(tmp).size - 1) {
        s = s + droppedCardsList(tmp)(tmp2).getCardName
      }
    }
    s
  }

  def grabGraveYard(): Option[Card] = {
    if(graveYard.getCardName.equals("",""))
      None
    end if
    val returnCard = graveYard // safe the graveYard Card
    graveYard = Card(5, 13) // delete the graveYard
    Some(returnCard) // return the Card
  }
}