package de.htwg.se.romme.model

class Card(suit: Integer, rank: Integer) {

  val suitList: List[String] =
    List("Heart", "Diamond", "Club", "Spades", "Joker")
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

}
