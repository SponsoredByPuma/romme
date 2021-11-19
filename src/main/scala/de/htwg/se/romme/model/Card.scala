package de.htwg.se.romme.model

trait Card {
  def getSuit: String
  def getValue: Integer
  def getCardName: (String, String)
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
    "ace"
  )
}

private class Heart(rank: Integer) extends Card {
  type Card = Heart
  override def getSuit: String = return "Heart"
  override def getValue: Integer = {
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
    }
  }
  override def getCardName: (String, String) = {
    return ("Heart", rankList(rank))
  }
}
private class Diamond(rank: Integer) extends Card {
  override def getSuit: String = return "Diamond"
  override def getValue: Integer = {
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
    }
  }
  override def getCardName: (String, String) = {
    return ("Diamond", rankList(rank))
  }
}
private class Spades(rank: Integer) extends Card {
  override def getSuit: String = return "Spades"
  override def getValue: Integer = {
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
    }
  }
  override def getCardName: (String, String) = {
    return ("Spades", rankList(rank))
  }
}
private class Club(rank: Integer) extends Card {
  override def getSuit: String = return "Club"
  override def getValue: Integer = {
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
    }
  }
  override def getCardName: (String, String) = {
    return ("Club", rankList(rank))
  }
}
private class Joker() extends Card {
  override def getSuit: String = return "Joker"
  override def getValue: Integer = return 10
  override def getCardName: (String, String) = {
    return ("Joker", "")
  }
}
private class EmptyCard() extends Card {
  override def getSuit: String = return ""
  override def getValue: Integer = return 0
  override def getCardName: (String, String) = {
    return ("", "")
  }
}

object Card {
  def apply(suit: Integer, rank: Integer) = suit match {
    case 0 => new Heart(rank)
    case 1 => new Diamond(rank)
    case 2 => new Club(rank)
    case 3 => new Spades(rank)
    case 4 => new Joker()
    case 5 => new EmptyCard()
  }
}

/*
class Card(var suit: Integer, var rank: Integer) {
  //e

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
 */
