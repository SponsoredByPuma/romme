package de.htwg.se.romme

package controller

import de.htwg.se.romme.util.Observer
import model.{Card, Deck, Player, PlayerHands, Table, Drops}

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
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.gameStart()
        observer.updated should be(true)
        controller.deck.deckList.size should be(97)
      }

      "notify its Observer after dropping a single Card" in {
        controller.dropASpecificCard(0)
        observer.updated should be(true)
        controller.hand.playerOneHand.size should be(12)
      }

      "notify its Observer after picking up the graveYard Card" in {
        controller.pickUpGraveYard()
        observer.updated should be(true)
        controller.hand.playerOneHand.size should be(13)
      }
      "notify its Observer after picking up a normal Card" in {
        controller.pickUpACard()
        observer.updated should be(true)
        controller.hand.playerOneHand.size should be(14)
      }

      "notify its Observer after checking victory" in {
        controller.victory() should be(false)
        observer.updated should be(true)
      }

      "notify its Observer after showing the cards" in {
        controller.showCards()
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
        var tmp = controller.hand.playerOneHand.size - 1
        for (x <- 0 to tmp)
          controller.hand.playerOneHand.remove(0)
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
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)

      controller.hand.playerOneHand.addOne(Card(0, 12))
      controller.hand.playerOneHand.addOne(Card(0, 11))
      controller.hand.playerOneHand.addOne(Card(0, 10))
      controller.hand.playerOneHand.addOne(Card(0, 9))
      controller.hand.playerOneHand.addOne(Card(0, 8))
      controller.hand.playerOneHand.addOne(Card(0, 7))
      println("---------------------------------------------------------")

      "notify its Observer after dropping multiple Cards in Order" in {
        var list: ListBuffer[Integer] = new ListBuffer()
        for (x <- 0 to 5)
          list.addOne(x)
        controller.dropMultipleCards(list, 1)
        observer.updated should be(true)
        controller.hand.playerOneHand.size should be(0)
      }
    }
  }
  "A Controller" when {
    "created and observed by an Observer" should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      hand.playerOneHand.addOne(Card(0, 0)) // herz 2
      hand.playerOneHand.addOne(Card(0, 0))
      var l: ListBuffer[Card] = ListBuffer()
      l.addOne(Card(0, 0))
      l.addOne(Card(1, 0))
      l.addOne(Card(2, 0))
      l.addOne(Card(3, 0))
      table.droppedCardsList.insert(0, l)
      table.droppedCardsList.insert(1, l)
      print(table.droppedCardsList(0)(0).getCardName)

      "notify its Observer" in {
        controller.takeJoker(0, 0)
        observer.updated should be(true)
      }
    }
  }
}
