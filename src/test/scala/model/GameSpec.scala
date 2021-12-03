package de.htwg.se.romme

package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.collection.mutable.ListBuffer

class GameSpec() extends AnyWordSpec {
  "A Game " when {
    "created" should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val game = new Game(table, hand, deck)

      "return true on victory" in {
        game.victory() should be(true)
      }
      "create a new Deck with and drop 13 Cards to the player " in {
        game.gameStart()
        game.hand.playerOneHand.size should be(13)
        game.deck.deckList.size should be(97)
      }
      "be able to pick up a card from the deck" in {
        game.pickUpACard()
        game.hand.playerOneHand.size should be(14)
        game.deck.deckList.size should be(96)
      }
      "be able to drop a specific card from the hand" in {
        val tmp = game.hand.playerOneHand(0)
        game.dropASpecificCard(0)
        game.hand.playerOneHand.size should be(13)
        game.table.graveYard.getCardName should be(tmp.getCardName)
      }
      "sort the players cards from the hand" in {
        val tmp = game.hand.playerOneHand
        game.sortPlayersCards()
        tmp should not be (game.hand.playerOneHand)
      }
      "show the players card on the hand" in {
        var t = game.showCards()
        t.isEmpty should be(false)
      }
      "show the table " in {
        var s = game.showTable()
        s.isEmpty should be(false)
      }
    }
  }

  "A Game " when {
    "starting with certain cards" should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      hand.playerOneHand.addOne(Card(0, 12))
      hand.playerOneHand.addOne(Card(0, 11))
      hand.playerOneHand.addOne(Card(0, 10))
      hand.playerOneHand.addOne(Card(0, 9))
      hand.playerOneHand.addOne(Card(0, 8))
      hand.playerOneHand.addOne(Card(0, 7)) // Card zum anlegen
      val game = new Game(table, hand, deck)
      var list: ListBuffer[Integer] = ListBuffer()
      list.addOne(0)
      list.addOne(1)
      list.addOne(2)
      list.addOne(3)
      list.addOne(4)

      "be able to drop multiple cards on the table" in {
        game.dropMultipleCards(list, 1) //--------------------------0
        game.hand.playerOneHand.size should be(1)
        game.table.droppedCardsList(0).size should be(5)
      }
      "be able to add a Card to a list on the table" in {
        game.addCard(0, 0)
        game.hand.playerOneHand.size should be(0)
        game.table.droppedCardsList(0).size should be(6)
      }
      "be able to add a Card to a Suit Ordered List on the table" in {
        game.hand.playerOneHand.addOne(Card(0, 12))
        game.hand.playerOneHand.addOne(Card(1, 12))
        game.hand.playerOneHand.addOne(Card(2, 12))
        game.hand.playerOneHand.addOne(Card(3, 12))
        var list2: ListBuffer[Integer] = ListBuffer()
        list2.addOne(0)
        list2.addOne(1)
        list2.addOne(2)
        game.dropMultipleCards(list2, 0) // --------------------------1
        game.hand.playerOneHand.size should be(1)
        game.table.droppedCardsList(1).size should be(3)
        game.addCard(0, 1)
        game.hand.playerOneHand.size should be(0)
        game.table.droppedCardsList(1).size should be(4)
      }
      "not be able to add a Card to a full set of Cards on the table" in {
        game.hand.playerOneHand.addOne(Card(0, 12))
        game.hand.playerOneHand.addOne(Card(1, 12))
        game.hand.playerOneHand.addOne(Card(2, 12))
        game.hand.playerOneHand.addOne(Card(3, 12))
        game.hand.playerOneHand.addOne(Card(3, 12))
        var list2: ListBuffer[Integer] = ListBuffer()
        list2.addOne(0)
        list2.addOne(1)
        list2.addOne(2)
        list2.addOne(3)
        game.dropMultipleCards(
          list2,
          0
        ) // ------------------------------------2
        game.hand.playerOneHand.size should be(1)
        game.table.droppedCardsList(2).size should be(4)
        game.addCard(0, 2)
        game.hand.playerOneHand.size should be(1)
        game.table.droppedCardsList(2).size should be(4)
      }
    }
  }

  "A Game" when {
    "created for testing" should {

      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val game = new Game(table, hand, deck)
      game.hand.playerOneHand.addOne(Card(0, 12))
      game.hand.playerOneHand.addOne(Card(0, 11))
      game.hand.playerOneHand.addOne(Card(0, 10))
      game.hand.playerOneHand.addOne(Card(0, 9))
      game.hand.playerOneHand.addOne(Card(0, 8))
      game.hand.playerOneHand.addOne(Card(0, 0))
      game.hand.playerOneHand.addOne(Card(0, 2))
      //game.hand.playerOneHand.addOne(Card(0, 4)) // Card zum anlegen
      var list3: ListBuffer[Integer] = ListBuffer()
      list3.addOne(0)
      list3.addOne(1)
      list3.addOne(2)
      list3.addOne(3)
      list3.addOne(4)
      list3.addOne(5)

      "not be able to add a wrong card to a set on the table" in {
        game.dropMultipleCards(list3, 1) // --------------------3
        game.hand.playerOneHand.size should be(1)
        game.table.droppedCardsList(0).size should be(6)
        game.addCard(0, 0)
        game.hand.playerOneHand.size should be(1)
        game.table.droppedCardsList(0).size should be(6)
      }

      "not be able to add a wrong card to a set on the table(Suit)" in {
        game.hand.playerOneHand.addOne(Card(1, 2))
        game.hand.playerOneHand.addOne(Card(2, 2))
        game.hand.playerOneHand.addOne(Card(3, 3))
        var list4: ListBuffer[Integer] = ListBuffer()
        list4.addOne(0)
        list4.addOne(1)
        list4.addOne(2)
        game.dropMultipleCards(list4, 0) // --------------------3
        game.hand.playerOneHand.size should be(1)
        game.table.droppedCardsList(1).size should be(3)
        game.addCard(0, 1)
        game.hand.playerOneHand.size should be(1)
        game.table.droppedCardsList(1).size should be(3)
      }

    }
  }

}
