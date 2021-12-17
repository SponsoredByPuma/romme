package de.htwg.se.romme

package model.modelComponent.gameBaseImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.collection.mutable.ListBuffer

class GameSpec() extends AnyWordSpec {
  "A Game " when {
    "created" should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val hand2 = new PlayerHands(table)
      val player = new Player("Player 1", hand, table)
      val player2 = new Player("Player 2", hand2, table)
      val game = new Game(table, player, player2, deck)

      "return true on victory" in {
        game.victory(true) should be(true)
      }
      "create a new Deck with and drop 13 Cards to the player " in {
        game.gameStart
        game.player.hands.playerOneHand.size should be(13)
        game.deck.deckList.size should be(84)
      }
      "be able to pick up a card from the deck" in {
        game.pickUpACard(true)
        game.player.hands.playerOneHand.size should be(14)
        game.deck.deckList.size should be(83)
        game.pickUpACard(false)
        game.player2.hands.playerOneHand.size should be(14)
        game.deck.deckList.size should be(82)
      }
      "be able to drop a specific card from the hand" in {
        val tmp = game.player.hands.playerOneHand(0)
        game.dropASpecificCard(0, true)
        game.player.hands.playerOneHand.size should be(13)
        game.table.graveYard.getCardName should be(tmp.getCardName)
      }
      "be able to pick up the graveyard card " in {
        val tmp = game.player2.hands.playerOneHand(0)
        game.dropASpecificCard(0, false)
        game.player2.hands.playerOneHand.size should be(13)
        game.table.graveYard.getCardName should be(tmp.getCardName)
      }
      "sort the players cards from the hand" in {
        val tmp = game.player.hands.playerOneHand
        game.sortPlayersCards(true)
        tmp should not be (game.player.hands.playerOneHand)
      }
      "show the players card on the hand" in {
        var t = game.showCards(true)
        t.isEmpty should be(false)
      }
      "show the table " in {
        var s = game.showTable
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
      val hand2 = new PlayerHands(table)
      val player = new Player("Player 1", hand, table)
      val player2 = new Player("Player 2", hand2, table)
      val game = new Game(table, player, player2, deck)
      var list: ListBuffer[Integer] = ListBuffer()
      list.addOne(0)
      list.addOne(1)
      list.addOne(2)
      list.addOne(3)
      list.addOne(4)

      "be able to drop multiple cards on the table" in {
        game.dropMultipleCards(
          list,
          1,
          true,
          false
        ) //--------------------------0
        game.player.hands.playerOneHand.size should be(1)
        game.table.droppedCardsList(0).size should be(5)
      }
      "be able to add a Card to a list on the table" in {
        game.addCard(0, 0, true)
        game.player.hands.playerOneHand.size should be(0)
        game.table.droppedCardsList(0).size should be(6)
      }
      "be able to add a Card to a Suit Ordered List on the table" in {
        game.player.hands.playerOneHand.addOne(Card(0, 12))
        game.player.hands.playerOneHand.addOne(Card(1, 12))
        game.player.hands.playerOneHand.addOne(Card(2, 12))
        game.player.hands.playerOneHand.addOne(Card(3, 12))
        var list2: ListBuffer[Integer] = ListBuffer()
        list2.addOne(0)
        list2.addOne(1)
        list2.addOne(2)
        game.dropMultipleCards(
          list2,
          0,
          true,
          false
        ) // --------------------------1
        game.player.hands.playerOneHand.size should be(1)
        game.table.droppedCardsList(1).size should be(3)
        game.addCard(0, 1, true)
        game.player.hands.playerOneHand.size should be(0)
        game.table.droppedCardsList(1).size should be(4)
      }
      "not be able to add a Card to a full set of Cards on the table" in {
        game.player.hands.playerOneHand.addOne(Card(0, 12))
        game.player.hands.playerOneHand.addOne(Card(1, 12))
        game.player.hands.playerOneHand.addOne(Card(2, 12))
        game.player.hands.playerOneHand.addOne(Card(3, 12))
        game.player.hands.playerOneHand.addOne(Card(3, 12))
        var list2: ListBuffer[Integer] = ListBuffer()
        list2.addOne(0)
        list2.addOne(1)
        list2.addOne(2)
        list2.addOne(3)
        game.dropMultipleCards(
          list2,
          0,
          true,
          false
        ) // ------------------------------------2
        game.player.hands.playerOneHand.size should be(1)
        game.table.droppedCardsList(2).size should be(4)
        game.addCard(0, 2, true)
        game.player.hands.playerOneHand.size should be(1)
        game.table.droppedCardsList(2).size should be(4)
      }
    }
  }

  "A Game" when {
    "created for testing" should {

      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val hand2 = new PlayerHands(table)
      val player = new Player("Player 1", hand, table)
      val player2 = new Player("Player 2", hand2, table)
      val game = new Game(table, player, player2, deck)
      game.player.hands.playerOneHand.addOne(Card(0, 12))
      game.player.hands.playerOneHand.addOne(Card(0, 11))
      game.player.hands.playerOneHand.addOne(Card(0, 10))
      game.player.hands.playerOneHand.addOne(Card(0, 9))
      game.player.hands.playerOneHand.addOne(Card(0, 8))
      game.player.hands.playerOneHand.addOne(Card(0, 0))
      game.player.hands.playerOneHand.addOne(Card(0, 2))
      //game.hand.playerOneHand.addOne(Card(0, 4)) // Card zum anlegen
      var list3: ListBuffer[Integer] = ListBuffer()
      list3.addOne(0)
      list3.addOne(1)
      list3.addOne(2)
      list3.addOne(3)
      list3.addOne(4)
      list3.addOne(5)

      "not be able to add a wrong card to a set on the table" in {
        game.dropMultipleCards(list3, 1, true, false) // --------------------3
        game.player.hands.playerOneHand.size should be(1)
        game.table.droppedCardsList(0).size should be(6)
        game.addCard(0, 0, true)
        game.player.hands.playerOneHand.size should be(1)
        game.table.droppedCardsList(0).size should be(6)
      }

      "not be able to add a wrong card to a set on the table(Suit)" in {
        game.player.hands.playerOneHand.addOne(Card(1, 2))
        game.player.hands.playerOneHand.addOne(Card(2, 2))
        game.player.hands.playerOneHand.addOne(Card(3, 3))
        var list4: ListBuffer[Integer] = ListBuffer()
        list4.addOne(0)
        list4.addOne(1)
        list4.addOne(2)
        game.dropMultipleCards(list4, 0, true, false) // --------------------3
        game.player.hands.playerOneHand.size should be(1)
        game.table.droppedCardsList(1).size should be(3)
        game.addCard(0, 1, true)
        game.player.hands.playerOneHand.size should be(1)
        game.table.droppedCardsList(1).size should be(3)
      }

    }
  }

  "A Game3" when {
    "created for testing" should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val hand2 = new PlayerHands(table)
      val player = new Player("Player 1", hand, table)
      val player2 = new Player("Player 2", hand2, table)
      val game = new Game(table, player, player2, deck)

      game.player.hands.playerOneHand.addOne(Card(4, 0))
      game.player2.hands.playerOneHand.addOne(Card(4, 0))
      var l: ListBuffer[Integer] = ListBuffer()
      var r: ListBuffer[String] = ListBuffer()
      var s: ListBuffer[String] = ListBuffer()
      l.addOne(0)
      r.addOne("king")
      s.addOne("Heart")
      "replace the Joker in the hands(rank)" in {
        game.replaceCardOrder(l, r, true)
        game.player.hands.playerOneHand(0).getValue should be(10)
        game.replaceCardOrder(l, r, false)
        game.player2.hands.playerOneHand(0).getValue should be(10)
      }
      game.player.hands.playerOneHand.addOne(Card(4, 0))
      game.player2.hands.playerOneHand.addOne(Card(4, 0))
      var t: ListBuffer[Integer] = ListBuffer()
      t.addOne(1)
      "replace the Joker in the hands(Suit)" in {
        game.replaceCardSuit(t, s, true)
        game.player.hands.playerOneHand(1).getSuit should be("Heart")
        game.replaceCardSuit(t, s, false)
        game.player2.hands.playerOneHand(1).getSuit should be("Heart")
      }
      val test: ListBuffer[Card] = ListBuffer()
      var c = Joker()
      c.setValue("jack")
      test.addOne(Card(0, 12))
      test.addOne(Card(0, 11))
      test.addOne(Card(0, 10))
      test.addOne(c)
      game.table.droppedCardsList.addOne(test)
      game.table.droppedCardsList.addOne(test)
      game.table.droppedCardsList.addOne(test)
      game.table.droppedCardsList.addOne(test)
      game.player.hands.playerOneHand.addOne(Card(0, 9))
      game.player2.hands.playerOneHand.addOne(Card(0, 9))
      "take the Joker on the board with the right Card" in {
        game.takeJoker(0, 2, true)
        game.player.hands.playerOneHand(2).getCardName should be("Joker", "")
        game.takeJoker(1, 2, false)
        game.player2.hands.playerOneHand(2).getCardName should be("Joker", "")
      }
      "not be able to take the Joker with the wrong Card" in {
        game.player.hands.playerOneHand.addOne(Card(0, 8))
        game.player2.hands.playerOneHand.addOne(Card(0, 8))
        game.takeJoker(2, 3, true)
        game.player.hands.playerOneHand(3).getCardName should be("Heart", "ten")
        game.takeJoker(3, 3, false)
        game.player2.hands.playerOneHand(3).getCardName should be(
          "Heart",
          "ten"
        )
      }
    }
  }

}
