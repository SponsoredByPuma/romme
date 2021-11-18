package de.htwg.se.romme

package controller

import de.htwg.se.romme.util.Observer
import model.{Card, Deck, Player, PlayerHands, Table}

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.language.reflectiveCalls

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

      "notify its Observer after picking up the graveYard Card" in {
        controller.dropASpecificCardTEST()
        controller.pickUpGraveYard()
        observer.updated should be(true)
        controller.hand.playerOneHand.size should be(13)
      }
      "notify its Observer after picking up a normal Card" in {
        controller.pickUpACard()
        observer.updated should be(true)
        controller.hand.playerOneHand.size should be(14)
      }
      /* // not possible since it needs an input
      "notify its Observer after dropping multiple Cards" in {
        controller.dropMultipleCards()
        //observer.updated should be(true)
        controller.hand.playerOneHand.size should be(10)
      }
       */
      "notify its Observer after checking victory" in {
        controller.victory() should be(false)
        observer.updated should be(true)
      }

      "notify its Observer after droppping a Card" in {
        controller.dropASpecificCardTEST()
        controller.hand.playerOneHand.size should be(13)
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

    }
  }

}
