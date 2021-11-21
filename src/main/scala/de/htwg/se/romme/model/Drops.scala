package de.htwg.se.romme

package model

import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine

object Drops {

  abstract class Drops() {
    def strategy(numberOfStrategy: Integer): Integer

    def strategySameSuit(): Integer

    def strategyOrder(): Integer

    def execute(cards: ListBuffer[Card], numberOfStrategy: Integer): Integer

  }

  def execute(cards: ListBuffer[Card], numberOfStrategy: Integer): Integer =
    strategy(numberOfStrategy, cards)

  def strategy(numberOfStrategy: Integer, cards: ListBuffer[Card]): Integer = {
    var sum = 0
    numberOfStrategy match {
      case 0 => sum = strategySameSuit(cards)
      case 1 => sum = strategyOrder(cards)
    }
    return sum
  }

  def strategySameSuit(cards: ListBuffer[Card]): Integer = {
    var sum = 0
    var tmpRank = 20
    var counter = 0

    if(cards.size > 4) // it can only be 4 cards at max
      return 0
    end if

    while (tmpRank == 20)
      if (cards(counter).getSuit.equals("Joker")) // if it is a Joker take the next card
        counter = counter + 1
      else
        tmpRank = cards(counter).getValue
    
    var tmpList: ListBuffer[Integer] = ListBuffer()
    for (x <- 0 to (cards.size - 1))
      if (cards(x).getSuit.equals("Joker"))
        tmpList.addOne(x)
      end if
    for(int <- tmpList)
      print("Which Suit should your Joker have ?")
      var input = readLine()
      cards.remove(int)
      var c:Joker = Joker()
      c.setSuit(input)
      cards.addOne(c)

    var storeSuits: ListBuffer[String] = ListBuffer()
    for (card <- cards) // store all Suits in a list
      storeSuits.addOne(card.getSuit)  
    if (storeSuits.distinct.size != storeSuits.size) // are the duplicates in the list ? 
      return 0
    end if
    var storeRanks: ListBuffer[Integer] = ListBuffer()
    for (card <- cards)
      storeRanks.addOne(card.getValue)
    if(storeRanks.distinct.size > (1 + tmpList.size)) // if there is more than one rank in the list
      return 0
    end if
    sum = tmpRank * cards.size
    return sum
  }

  def strategyOrder(cards: ListBuffer[Card]): Integer = {
    var tmpSuit = "Joker"
    var counter = 0
    var sum = 0
    var tmpList: ListBuffer[Integer] = ListBuffer()
    while(tmpSuit.equals("Joker")) // if the list starts with a joker you need to get the real Suit
        tmpSuit = cards(counter).getSuit
        counter = counter + 1
    for (x <- 0 to (cards.size - 1)) // check if all cards are the same suit
      if (cards(x).getSuit.equals(tmpSuit) || cards(x).getSuit.equals("Joker")) // 
        if (cards(x).getSuit.equals("Joker"))
          tmpList.addOne(x)
        end if 
      else
        return 0 // the cards have different Suits so its wrong
      end if

    for(int <- tmpList)
      print("Which Rank should your Joker have ?")
      var input = readLine()
      cards.remove(int)
      var c:Joker = Joker()
      c.setValue(input)
      cards.addOne(c)

    var list: ListBuffer[Card] = ListBuffer()
    list = cards.sortBy(_.placeInList)
    if(lookForGaps(list) == false)
      print("somethings fucked i can feel it")
      return 0
    end if

    for (dome <- list) // finally get the sum of the cards
      sum = sum + dome.getValue
    print(sum)
    return sum // gebe die Summe zurück 
  }

  def lookForGaps(list: ListBuffer[Card]): Boolean = {

    var next = list(0).placeInList
     
    for (x <- 0 until (list.size - 1)) // until, since the last card has no next 
      next = next + 1 // increase next for 1
      if (list(x).placeInList == 12) // falls es ein Ace ist, ist die nächste Karte wieder eine zwei
        next = 0
      end if
      if(list(x + 1).placeInList != next)
        return false
      end if
    return true
  }
}
