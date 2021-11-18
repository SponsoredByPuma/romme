package de.htwg.se.romme

package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.collection.mutable.ListBuffer

class PlayerHandsSpec extends AnyWordSpec {
  "A player Hands" when {
    "starting the game " should {
      val deck = Deck()
      val table = new Table()
      deck.createNewDeck()
      val hands = PlayerHands(table)
      "be 13 Cards " in {
        hands.draw13Cards(deck)
        hands.playerOneHand.size should be(13)
      }
      "be 12 Cards after dropping a single Card" in {
        hands.dropASingleCard(0)
        hands.playerOneHand.size should be(12)
      }
      "be able to drop 6 Cards from his hand and add them to the table" in {
        var testList: ListBuffer[Integer] = new ListBuffer()
        for (x <- 0 to 5)
          testList.addOne(x)
        hands.dropCardsOnTable(testList)
        table.droppedCardsList(0).size should be(6)
      }
    }
  }
}
