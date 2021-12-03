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
      val game = new Game(table, hand, deck)
      var list: ListBuffer[Integer] = ListBuffer()
      list.addOne(0)
      list.addOne(1)
      list.addOne(2)
      list.addOne(3)
      list.addOne(4)

      "be able to drop multiple cards on the table" in {
        game.dropMultipleCards(list, 1)
        game.hand.playerOneHand.size should be(0)
        game.table.droppedCardsList(0).size should be(5)
      }
    }
  }

}
