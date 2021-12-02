package de.htwg.se.romme

package controller

import de.htwg.se.romme.util.Observer
import model.{Card, Deck, Player, PlayerHands, Table, Drops, Game}

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer

class ControllerSpec extends AnyWordSpec {

  "A Controller" when {
    "observed by an Observer" should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val game = new Game(table, hand, deck)
      val controller = new Controller(game)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.gameStart()
        observer.updated should be(true)
        controller.game.deck.deckList.size should be(97)
      }

      "notify its Observer after dropping a single Card" in {
        controller.dropASpecificCard(0)
        observer.updated should be(true)
        controller.game.hand.playerOneHand.size should be(12)
      }

      "notify its Observer after picking up the graveYard Card" in {
        controller.pickUpGraveYard()
        observer.updated should be(true)
        controller.game.hand.playerOneHand.size should be(13)
      }

      "notify its Observer after picking up a normal Card" in {
        controller.pickUpACard()
        observer.updated should be(true)
        controller.game.hand.playerOneHand.size should be(14)
      }

      "notify its Observer after undo" in {
        controller.undo
        observer.updated should be(true)
        controller.game.deck.deckList.size should be(97) // 0
      }
      "notify its Observer after redo" in {
        controller.redo
        observer.updated should be(true)
        controller.game.deck.deckList.size should be(96) // 97
      }

      "notify its Observer after checking victory" in {
        controller.victory() should be(false)
        observer.updated should be(true)
      }

      "notify its Observer after showing the cards" in {
        controller.showCards() should be(true)
        observer.updated should be(true)
      }
      "notify its Observer after showing the Table" in {
        controller.showTable()
        observer.updated should be(true)
      }
      "notify its Observer after sorting the Cards" in {
        controller.sortPlayersCards()
        observer.updated should be(true)
      }
      "notify its Observer after checking vitory" in {
        var tmp = controller.game.hand.playerOneHand.size - 1
        for (x <- 0 to tmp)
          controller.game.hand.playerOneHand.remove(0)
        controller.victory() should be(true)
        observer.updated should be(true)
      }
    }
  }
  "A Controller" when {
    "observed by an Observer" should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val game = new Game(table, hand, deck)
      val controller = new Controller(game)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)

      controller.game.hand.playerOneHand.addOne(Card(0, 12))
      controller.game.hand.playerOneHand.addOne(Card(0, 11))
      controller.game.hand.playerOneHand.addOne(Card(0, 10))
      controller.game.hand.playerOneHand.addOne(Card(0, 9))
      controller.game.hand.playerOneHand.addOne(Card(0, 8))
      controller.game.hand.playerOneHand.addOne(Card(0, 7))
      println("---------------------------------------------------------")

      "notify its Observer after dropping multiple Cards in Order" in {
        var list: ListBuffer[Integer] = new ListBuffer()
        for (x <- 0 to 5)
          list.addOne(x)
        controller.dropMultipleCards(list, 1)
        observer.updated should be(true)
        controller.game.hand.playerOneHand.size should be(0)
      }
    }
  }

}
