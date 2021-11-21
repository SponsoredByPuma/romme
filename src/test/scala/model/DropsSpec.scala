package de.htwg.se.romme

package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.collection.mutable.ListBuffer

class DropsSpec extends AnyWordSpec {
  "A Drops" when {
    "created" should {
      val drops = Drops
      var list: ListBuffer[Card] = ListBuffer()
      list.addOne(Card(0, 12))
      list.addOne(Card(1, 12))
      list.addOne(Card(2, 12))
      list.addOne(Card(3, 12))
      "execute strategySame Suit and return 40 as sum" in {
        drops.execute(list, 0) should be(40)
      }
      "execute strategyOrder and return 0 as sum" in {
        drops.execute(list, 1) should be(0)
      }
    }
    "created " should {
      val drops = Drops
      var list: ListBuffer[Card] = ListBuffer()
      list.addOne(Card(0, 12))
      list.addOne(Card(0, 11))
      list.addOne(Card(0, 10))
      list.addOne(Card(0, 9))
      "execute strategySame Suit and return 0 as sum" in {
        drops.execute(list, 0) should be(0)
      }
      "execute strategyOrder and return 40 as sum" in {
        drops.execute(list, 1) should be(40)
      }
    }
    "there are gaps in the list " should {
      val drops = Drops
      var list: ListBuffer[Card] = ListBuffer()
      list.addOne(Card(0, 12))
      list.addOne(Card(0, 11))
      list.addOne(Card(0, 10))
      list.addOne(Card(0, 9))
      list.addOne(Card(0, 6))
      "execute strategySame Suit and return 0 as sum" in {
        drops.execute(list, 0) should be(0)
      }
      "execute strategyOrder and return 0 as sum" in {
        drops.execute(list, 1) should be(0)
      }
    }
    /* // readLine in drops :(
    "created with Jokers" should {
      val drops = Drops
      var list: ListBuffer[Card] = ListBuffer()
      list.addOne(Card(0, 12))
      list.addOne(Card(0, 11))
      list.addOne(Card(0, 10))
      list.addOne(Card(4, 9))
      "execute strategySame Suit and return 0 as sum" in {
        drops.execute(list, 0) should be(0)
      }
      "execute strategyOrder and return 40 as sum" in {
        drops.execute(list, 1) should be(40)
      }
    }
    "created with Jokers" should {
      val drops = Drops
      var list: ListBuffer[Card] = ListBuffer()
      list.addOne(Card(1, 12))
      list.addOne(Card(2, 12))
      list.addOne(Card(3, 12))
      list.addOne(Card(4, 9))
      "execute strategySame Suit and return 40 as sum" in {
        drops.execute(list, 0) should be(40)
      }
      "execute strategyOrder and return 0 as sum" in {
        drops.execute(list, 1) should be(0)
      }
    }
     */
  }
}
