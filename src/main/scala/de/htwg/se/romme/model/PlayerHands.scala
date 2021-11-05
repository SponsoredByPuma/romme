package de.htwg.se.romme

package model

import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.Deck

class PlayerHands() {
  var playerOneHand: ListBuffer[de.htwg.se.romme.model.Card] = new ListBuffer()

  def draw13Cards(d: Deck): Unit = {
    var counter = 0

    for (counter <- 0 to 12) {
      playerOneHand.addOne(d.drawFromDeck())
    }
  }

  /*
  def dropOneCard(index: Integer): Unit = {
    playerOneHand.remove(index)
  }
   */
}
