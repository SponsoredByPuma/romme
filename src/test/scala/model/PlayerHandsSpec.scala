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

      "be sorted by rank" in {
        hands.sortMyCards()
        var check =
          hands.playerOneHand(0).placeInList <= hands
            .playerOneHand(1)
            .placeInList
        check should be(true)
      }
    }
  }

  "A PlayersHand" when {
    "created " should {
      val deck = Deck()
      val table = new Table()
      val hand = PlayerHands(table) // ich hasse random sachen

      "be able to drop 5 Cards from his hand in Order and add them to the table" in {
        hand.playerOneHand.addOne(Card(0, 12))
        hand.playerOneHand.addOne(Card(0, 11))
        hand.playerOneHand.addOne(Card(0, 10))
        hand.playerOneHand.addOne(Card(0, 9))
        hand.playerOneHand.addOne(Card(0, 8))
        var testList: ListBuffer[Integer] = new ListBuffer()
        for (x <- 0 to 4)
          testList.addOne(x)
        hand.dropCardsOnTable(testList, 1) should be(true)
        table.droppedCardsList(0).size should be(5)
      }
      "be able to drop 3 Cards from his hand and add them to the table" in {
        println("DU HUREERERERERERER")
        hand.playerOneHand.addOne(Card(0, 12))
        hand.playerOneHand.addOne(Card(1, 12))
        hand.playerOneHand.addOne(Card(2, 12))
        hand.playerOneHand.addOne(Card(3, 12))
        var testList: ListBuffer[Integer] = new ListBuffer()
        for (x <- 5 to 8)
          testList.addOne(x)
        hand.dropCardsOnTable(testList, 0) should be(true)
        table.droppedCardsList(1).size should be(4)
      }
    }
  }
  "A PlayersHand" when {
    "no idea for a name" should {
      val deck = Deck()
      val table = new Table()
      val hand = PlayerHands(table)

      "not be able to drop 4 Cards from his hand in Order and add them to the table" in {
        hand.playerOneHand.addOne(Card(0, 0))
        hand.playerOneHand.addOne(Card(0, 1))
        hand.playerOneHand.addOne(Card(0, 2))
        hand.playerOneHand.addOne(Card(0, 3))
        var testList: ListBuffer[Integer] = new ListBuffer()
        for (x <- 0 to 3)
          testList.addOne(x)
        hand.dropCardsOnTable(testList, 1) should be(false)
        table.droppedCardsList.size should be(0)
      }
      "be able to drop 3 Cards from his hand and add them to the table" in {
        hand.playerOneHand.addOne(Card(0, 12))
        hand.playerOneHand.addOne(Card(1, 12))
        hand.playerOneHand.addOne(Card(2, 12))
        hand.playerOneHand.addOne(Card(3, 12))
        var testList: ListBuffer[Integer] = new ListBuffer()
        for (x <- 5 to 7)
          testList.addOne(x)
        hand.dropCardsOnTable(testList, 1) should be(false)
        table.droppedCardsList.size should be(0)
      }
    }
  }

}
