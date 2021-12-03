package de.htwg.se.romme

package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.collection.mutable.ListBuffer

class PlayerSpec extends AnyWordSpec {
  "A Player " when {
    "created" should {
      val table = new Table()
      val hand = new PlayerHands(table)
      val player = Player("Player 1", hand, table)
      val deck = new Deck()
      deck.createNewDeck()
      "return Player 1 as its name" in {
        player.getName should be("Player 1")
      }
      "be able to pick up a Card" in {
        player.pickUpACard(deck)
        player.hands.playerOneHand.size should be(1)
      }
      "be able to drop a Card" in {
        player.dropASpecificCard(0)
        player.hands.playerOneHand.size should be(0)
      }
      "be able to check the victory and win" in {
        player.victory should be(true)
      }
      "be able to pick up the graveYard Card" in {
        player.pickUpGraveYard
        player.hands.playerOneHand.size should be(1)
      }
      "be able to check the victory " in {
        player.victory should be(false)
      }
      "be able to show his cards" in {
        var s = player.showCards
        s.isEmpty should be(false)
      }
      "be able to show the table" in {
        var s = player.showTable
        s.isEmpty should be(false)
      }
    }
  }
  "A Player " when {
    "created" should {
      val table = new Table()
      val hand = new PlayerHands(table)
      val player = Player("Player 1", hand, table)

      player.hands.playerOneHand.addOne(Card(0, 12))
      player.hands.playerOneHand.addOne(Card(0, 11))
      player.hands.playerOneHand.addOne(Card(0, 10))
      player.hands.playerOneHand.addOne(Card(0, 9))
      player.hands.playerOneHand.addOne(Card(0, 8))
      player.hands.playerOneHand.addOne(Card(0, 7)) // Card zum anlegen
      var list: ListBuffer[Integer] = ListBuffer()
      list.addOne(0)
      list.addOne(1)
      list.addOne(2)
      list.addOne(3)
      list.addOne(4)

      "be able to drop multiple cards on the table" in {
        player.dropMultipleCards(list, 1) //--------------------------0
        player.hands.playerOneHand.size should be(1)
        table.droppedCardsList(0).size should be(5)
      }
      "be able to add a Card to a list on the table" in {
        player.addCard(0, 0)
        player.hands.playerOneHand.size should be(0)
        player.table.droppedCardsList(0).size should be(6)
      }
      "be able to add a Card to a Suit Ordered List on the table" in {
        player.hands.playerOneHand.addOne(Card(0, 12))
        player.hands.playerOneHand.addOne(Card(1, 12))
        player.hands.playerOneHand.addOne(Card(2, 12))
        player.hands.playerOneHand.addOne(Card(3, 12))
        var list2: ListBuffer[Integer] = ListBuffer()
        list2.addOne(0)
        list2.addOne(1)
        list2.addOne(2)
        player.dropMultipleCards(list2, 0) // --------------------------1
        player.hands.playerOneHand.size should be(1)
        player.table.droppedCardsList(1).size should be(3)
        player.addCard(0, 1)
        player.hands.playerOneHand.size should be(0)
        player.table.droppedCardsList(1).size should be(4)
      }
      "not be able to add a Card to a full set of Cards on the table" in {
        player.hands.playerOneHand.addOne(Card(0, 12))
        player.hands.playerOneHand.addOne(Card(1, 12))
        player.hands.playerOneHand.addOne(Card(2, 12))
        player.hands.playerOneHand.addOne(Card(3, 12))
        player.hands.playerOneHand.addOne(Card(3, 12))
        var list2: ListBuffer[Integer] = ListBuffer()
        list2.addOne(0)
        list2.addOne(1)
        list2.addOne(2)
        list2.addOne(3)
        player.dropMultipleCards(
          list2,
          0
        ) // ------------------------------------2
        player.hands.playerOneHand.size should be(1)
        player.table.droppedCardsList(2).size should be(4)
        player.addCard(0, 2)
        player.hands.playerOneHand.size should be(1)
        player.table.droppedCardsList(2).size should be(4)
      }
    }
  }

  "A Player" when {
    "created for testing" should {

      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val player = new Player("Player 1", hand, table)
      player.hands.playerOneHand.addOne(Card(0, 12))
      player.hands.playerOneHand.addOne(Card(0, 11))
      player.hands.playerOneHand.addOne(Card(0, 10))
      player.hands.playerOneHand.addOne(Card(0, 9))
      player.hands.playerOneHand.addOne(Card(0, 8))
      player.hands.playerOneHand.addOne(Card(0, 0))
      player.hands.playerOneHand.addOne(Card(0, 2))
      var list3: ListBuffer[Integer] = ListBuffer()
      list3.addOne(0)
      list3.addOne(1)
      list3.addOne(2)
      list3.addOne(3)
      list3.addOne(4)
      list3.addOne(5)

      "not be able to add a wrong card to a set on the table" in {
        player.dropMultipleCards(list3, 1) // --------------------3
        player.hands.playerOneHand.size should be(1)
        player.table.droppedCardsList(0).size should be(6)
        player.addCard(0, 0)
        player.hands.playerOneHand.size should be(1)
        player.table.droppedCardsList(0).size should be(6)
      }

      "not be able to add a wrong card to a set on the table(Suit)" in {
        player.hands.playerOneHand.addOne(Card(1, 2))
        player.hands.playerOneHand.addOne(Card(2, 2))
        player.hands.playerOneHand.addOne(Card(3, 3))
        var list4: ListBuffer[Integer] = ListBuffer()
        list4.addOne(0)
        list4.addOne(1)
        list4.addOne(2)
        player.dropMultipleCards(list4, 0) // --------------------3
        player.hands.playerOneHand.size should be(1)
        player.table.droppedCardsList(1).size should be(3)
        player.addCard(0, 1)
        player.hands.playerOneHand.size should be(1)
        player.table.droppedCardsList(1).size should be(3)
      }

    }
  }

}
