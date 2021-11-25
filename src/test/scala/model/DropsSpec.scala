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
      var sum = 0
      "execute strategySame Suit and return 40 as sum" in {
        list = drops.execute(list, 0)
        for (l <- list)
          sum = list.size * l.getValue
        sum should be(40)
      }
      "execute strategyOrder and return 0 as sum" in {
        list = drops.execute(list, 1)
        list.size should be(0)
      }

      "created " should {
        val drops = Drops
        var list: ListBuffer[Card] = ListBuffer()
        list.addOne(Card(0, 12))
        list.addOne(Card(0, 11))
        list.addOne(Card(0, 10))
        list.addOne(Card(0, 9))
        /*
        "execute strategySame Suit and return 0 as sum" in {
          drops.execute(list, 0) should be(0)
        }
         */
        "execute strategyOrder and return 40 as sum" in {
          var sum = 0
          list = drops.execute(list, 1)
          for (l <- list)
            sum = sum + l.getValue
          sum should be(40)
        }
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
      "execute strategySame Suit and return a  list of the size of 0 " in {
        list = drops.execute(list, 0)
        list.size should be(0)
      }

      "execute strategyOrder and a list of the size of 0" in {
        var list2: ListBuffer[Card] = ListBuffer()
        list2.addOne(Card(0, 12))
        list2.addOne(Card(0, 11))
        list2.addOne(Card(0, 10))
        list2.addOne(Card(0, 9))
        list2.addOne(Card(0, 6))
        list2 = drops.execute(list2, 1)
        list2.size should be(0)
      }
    }
    "there is a order of queen king ace two three four five" should {
      val drops = Drops
      var list: ListBuffer[Card] = ListBuffer()
      list.addOne(Card(0, 12))
      list.addOne(Card(0, 11))
      list.addOne(Card(0, 10))
      list.addOne(Card(0, 0))
      list.addOne(Card(0, 1))
      list.addOne(Card(0, 2))
      list.addOne(Card(0, 3))
      "execute strategyOrder and return a correct list of cards which its sum is 44" in {

        var list2: ListBuffer[Card] = ListBuffer()
        list2 = drops.execute(list, 1)
        list2.size should be(7)
        var sum = 0
        for (card <- list2)
          sum = sum + card.getValue
        sum should be(44)
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
