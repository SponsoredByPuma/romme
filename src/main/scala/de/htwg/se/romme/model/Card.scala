package de.htwg.se.romme.model

class Card(var suit: Integer, var rank: Integer) {

  val suitList: List[String] =
    List("Heart", "Diamond", "Club", "Spades", "Joker", "") // ""
  val rankList: List[String] = List(
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine",
    "ten",
    "jack",
    "queen",
    "king",
    "ace",
    ""
  )

  def getCardName(): (String, String) = {
    val suitAsString = suitList(suit)
    val rankAsString = rankList(rank)
    return (suitAsString, rankAsString)
  }

  def getSuit(): String = {
    this.suit match {
      case 0 => return "Heart"
      case 1 => return "Diamond"
      case 2 => return "Club"
      case 3 => return "Spades"
      case 4 => return "Joker"
    }
  }

  def getValue(): Integer = {
    this.rank match {
      case 0  => return 2
      case 1  => return 3
      case 2  => return 4
      case 3  => return 5
      case 4  => return 6
      case 5  => return 7
      case 6  => return 8
      case 7  => return 9
      case 8  => return 10
      case 9  => return 10
      case 10 => return 10
      case 11 => return 10
      case 12 => return 10
      case 13 => return 10 // Joker, muss ich mir noch überlegen
    }
  }

}
