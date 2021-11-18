/*package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.Romme.Cards
import model.Romme.playerAction

class RommeSpec extends AnyWordSpec {

  "In the beginning" when {
    "the players get their Cards" should {
      "start with 13 Cards" in {
        //val test = new Romme()
        val test = new playerAction()
        val cards = test.gameStart()
        cards should be(13)
      }
    }
  }
  "A player" when {
    "he drops a single card" should {
      "have 12 Cards" in {
        val test = new playerAction()
        Romme.cardAmount = test.gameStart()
        test.dropSingleCard()
        Romme.cardAmount should be(12)
      }
    }
  }
  "A player" when {
    "he has 0 cards" should {
      "win" in {
        val test = new playerAction()
        Romme.cardAmount = test.gameStartForTesting()
        test.checkVictory() should equal(true)
      }
    }
  }
  "A player" when {
    "he has 13 cards and picks one up" should {
      "have 14 cards in his hand" in {
        val test = new playerAction()
        Romme.cardAmount = test.gameStart()
        Romme.cardAmount = test.pickUpACard()
        Romme.cardAmount should be(14)
      }
    }
  }

}
 */
