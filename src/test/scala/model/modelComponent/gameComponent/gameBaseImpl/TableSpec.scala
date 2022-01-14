package de.htwg.se.romme

package model.modelComponent.gameComponent.gameBaseImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.collection.mutable.ListBuffer

class TableSpec extends AnyWordSpec {
  "A Table" when {
    "a Card is dropped" should {
      var table = new Table()
      var deck = new Deck()
      var hand = new PlayerHands(table)
      deck.createNewDeck()
      hand.draw13Cards(deck)
      "have the dropped Card as its graveYard" in {
        hand.showYourCards()
        var tmp = hand.playerOneHand(0)
        hand.dropASingleCard(0)
        table.graveYard.getCardName should be(tmp.getCardName)
      }
      "be able to grab the graveYard Card" in {
        hand.dropASingleCard(0)
        var tmp = table.graveYard.getCardName
        var tmp2 = table.grabGraveYard()
        tmp2.get.getCardName should be(tmp)
      }
    }
  }
  "A Table" when {
    "a Card is dropped" should {
      var table = new Table()
      var deck = new Deck()
      var hand = new PlayerHands(table)
      deck.createNewDeck()
      hand.playerOneHand.addOne(Card(0, 12))
      hand.playerOneHand.addOne(Card(0, 11))
      hand.playerOneHand.addOne(Card(0, 10))
      hand.playerOneHand.addOne(Card(0, 9))
      hand.playerOneHand.addOne(Card(0, 8))

      "have the dropped Cards on the table" in {
        var testList: ListBuffer[Integer] = new ListBuffer()
        for (x <- 0 to 4)
          testList.addOne(x)
        hand.dropCardsOnTable(testList, 1, false)
        table.droppedCardsList.isEmpty should be(false)
      }
    }
  }
}
