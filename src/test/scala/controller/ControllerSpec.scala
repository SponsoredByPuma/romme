package de.htwg.se.romme

package controller

import de.htwg.se.romme.util.Observer
import model.modelComponent.DropsInterface
import model.modelComponent.GameInterface
import model.modelComponent.gameBaseImpl._
import controller.controllerComponent.controllerBaseImpl.Controller

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
      val hand2 = new PlayerHands(table)
      val player = new Player("Player 1", hand, table)
      val player2 = new Player("Player 2", hand2, table)
      val game = new Game(table, player, player2, deck)
      val controller = new Controller(game)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      //controller.add(observer)
      "notify its Observer after creation" in {
        controller.gameStart
        //observer.updated should be(true)
        controller.game.deck.deckList.size should be(84)
      }

      "notify its Observer after dropping a single Card" in {
        controller.dropASpecificCard(0)
        //observer.updated should be(true)
        controller.game.player.hands.playerOneHand.size should be(12)
      }

      "notify its Observer after picking up the graveYard Card" in {
        controller.pickUpGraveYard
        //observer.updated should be(true)
        controller.game.player.hands.playerOneHand.size should be(13)
      }

      "notify its Observer after picking up a normal Card" in {
        controller.pickUpACard
        //observer.updated should be(true)
        controller.game.player.hands.playerOneHand.size should be(14)
      }

      "notify its Observer after undo" in {
        controller.undo
        //observer.updated should be(true)
        controller.game.deck.deckList.size should be(84) // 0
      }
      "notify its Observer after redo" in {
        controller.redo
        //observer.updated should be(true)
        controller.game.deck.deckList.size should be(83) // 97
      }

      "notify its Observer after checking victory" in {
        controller.victory should be(false)
        // observer.updated should be(true)
      }

      "notify its Observer after showing the cards" in {
        var s = controller.showCards
        s.isEmpty should be(false)
        //observer.updated should be(true)
      }
      "notify its Observer after showing the Table" in {
        var s = controller.showTable
        s.isEmpty should be(false)
        //observer.updated should be(true)
      }
      "notify its Observer after sorting the Cards" in {
        controller.sortPlayersCards
        //observer.updated should be(true)
      }
      "notify its Observer after checking vitory" in {
        var tmp = controller.game.player.hands.playerOneHand.size - 1
        for (x <- 0 to tmp)
          controller.game.player.hands.playerOneHand.remove(0)
        controller.victory should be(true)
        //observer.updated should be(true)
      }
    }
  }
  "A Controller" when {
    "observed by an Observer" should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val hand2 = new PlayerHands(table)
      val player = new Player("Player 1", hand, table)
      val player2 = new Player("Player 2", hand2, table)
      val game = new Game(table, player, player2, deck)
      val controller = new Controller(game)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      //controller.add(observer)

      controller.game.player.hands.playerOneHand.addOne(Card(0, 12))
      controller.game.player.hands.playerOneHand.addOne(Card(0, 11))
      controller.game.player.hands.playerOneHand.addOne(Card(0, 10))
      controller.game.player.hands.playerOneHand.addOne(Card(0, 9))
      controller.game.player.hands.playerOneHand.addOne(Card(0, 8))
      controller.game.player.hands.playerOneHand.addOne(Card(0, 7))
      println("---------------------------------------------------------")

      "notify its Observer after dropping multiple Cards in Order" in {
        var list: ListBuffer[Integer] = new ListBuffer()
        for (x <- 0 to 5)
          list.addOne(x)
        controller.dropMultipleCards(list, 1, true)
        //observer.updated should be(true)
        controller.game.player.hands.playerOneHand.size should be(0)
      }
      "return the table" in {
        controller.getCardsTable.isEmpty should be(false)
      }
    }
  }

  "A controller" when {
    "used for more testing" should {
      val table = new Table()
      val deck = new Deck()
      val hand = new PlayerHands(table)
      val hand2 = new PlayerHands(table)
      val player = new Player("Player 1", hand, table)
      val player2 = new Player("Player 2", hand2, table)
      val game = new Game(table, player, player2, deck)
      val controller = new Controller(game)

      controller.game.player.hands.playerOneHand.addOne(Card(4, 0))
      controller.game.player.hands.playerOneHand.addOne(Card(0, 0))
      controller.game.player.hands.playerOneHand.addOne(Card(2, 0))
      controller.game.player.hands.playerOneHand.addOne(Card(1, 0))
      controller.game.player.hands.playerOneHand.addOne(Card(3, 0))

      controller.game.player2.hands.playerOneHand.addOne(Card(4, 0))
      controller.game.player2.hands.playerOneHand.addOne(Card(0, 0))
      controller.game.player2.hands.playerOneHand.addOne(Card(2, 0))
      controller.game.player2.hands.playerOneHand.addOne(Card(1, 0))
      controller.game.player2.hands.playerOneHand.addOne(Card(3, 0))

      var list: ListBuffer[Integer] = ListBuffer()
      for (x <- 0 to 4)
        list.addOne(x)
      "return the places of the jokers" in {
        controller.checkForJoker(list).isEmpty should be(false)
        controller.checkForJoker(list)(0) should be(0)
        controller.checkForJoker(list).size should be(1)
        controller.player1Turn = false
        controller.checkForJoker(list).isEmpty should be(false)
        controller.checkForJoker(list)(0) should be(0)
        controller.checkForJoker(list).size should be(1)
        //-----------Joker entfernen
        list.remove(4)
        controller.game.player2.hands.playerOneHand.remove(0)
        controller.checkForJoker(list).isEmpty should be(true)
        controller.checkForJoker(list).size should be(0)
        controller.player1Turn = true
        controller.game.player.hands.playerOneHand.remove(0)
        controller.checkForJoker(list).isEmpty should be(true)
        controller.checkForJoker(list).size should be(0)
      }
      controller.game.player2.hands.playerOneHand.addOne(Card(4, 0))
      controller.game.player.hands.playerOneHand.addOne(Card(4, 0))
      "replace a Joker with another Joker with the right Rank" in {
        var l: ListBuffer[Integer] = ListBuffer()
        var s: ListBuffer[String] = ListBuffer()
        l.addOne(4)
        s.addOne("king")
        controller.replaceCardOrder(l, s)
        controller.game.player.hands.playerOneHand(4).getValue should be(10)
      }

      "replace a Joker with another Joker with the right Suit" in {
        controller.player1Turn = false
        var l: ListBuffer[Integer] = ListBuffer()
        var s: ListBuffer[String] = ListBuffer()
        l.addOne(4)
        s.addOne("Heart")
        controller.replaceCardSuit(l, s)
        controller.game.player2.hands.playerOneHand(4).getSuit should be(
          "Heart"
        )
      }
      "switch players" in {
        controller.player1Turn should be(false)
        controller.switch
        controller.player1Turn should be(true)
      }
      "return the players Turn" in {
        controller.playersTurn should be(true)
      }
      "return the players Cards" in {
        controller.getCards.isEmpty should be(false)
        controller.switch
        controller.getCards.isEmpty should be(false)
      }
      "return the table " in {
        controller.getCardsTable.isEmpty should be(true)
      }
      "return the graveyard" in {
        var c = controller.game.player.hands.playerOneHand(0)
        controller.game.player.dropASpecificCard(0)
        controller.getGraveyardCard.getCardName should be(c.getCardName)
      }
      "be able to add a Card" in {
        var t: ListBuffer[Card] = ListBuffer()
        t.addOne(Card(0, 12))
        t.addOne(Card(0, 11))
        t.addOne(Card(0, 10))
        t.addOne(Card(0, 9))
        t.addOne(Card(0, 8))
        controller.game.player2.hands.playerOneHand.addOne(Card(0, 7))
        controller.game.table.droppedCardsList.addOne(t)
        controller.game.table.droppedCardsList(0).size should be(5)
        controller.addCard(5, 0)
        controller.game.table.droppedCardsList(0).size should be(6)
      }
    }
  }

}
