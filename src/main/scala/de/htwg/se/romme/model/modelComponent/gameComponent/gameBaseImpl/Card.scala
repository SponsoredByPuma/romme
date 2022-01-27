package de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl
trait Card {
  def getSuit: String
  def getValue: Integer 
  def getCardName: (String, String)
  def placeInList: Option[Integer]
  def getCardNameAsString: String
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
  val valueForCard = Map(0 -> 2, 1 -> 3, 2 -> 4, 3 -> 5, 4 -> 6, 5 -> 7, 6 -> 8, 7 -> 9, 8 -> 10, 9 -> 10, 10 -> 10, 11-> 10, 12 -> 10)
}

private class Heart(rank: Integer) extends Card {
  override def getSuit: String = "Heart"
  override def getValue: Integer = valueForCard.apply(rank)
  override def getCardName: (String, String) = ("Heart", rankList(rank))

  override def placeInList: Option[Integer] = Some(rank)

  override def getCardNameAsString: String = {
    var s = "(Heart," + rankList(rank) + ")"
    s
  }
}

private class Diamond(rank: Integer) extends Card {
  override def getSuit: String = "Diamond"
  override def getValue: Integer = valueForCard.apply(rank)
  override def getCardName: (String, String) = ("Diamond", rankList(rank))
  override def placeInList: Option[Integer] = Some(rank)

  override def getCardNameAsString: String = {
    var s = "(Diamond," + rankList(rank) + ")"
    s
  }
}

private class Spades(rank: Integer) extends Card {
  override def getSuit: String = "Spades"
  override def getValue: Integer = valueForCard.apply(rank)
  override def getCardName: (String, String) = ("Spades", rankList(rank))
  override def placeInList: Option[Integer] = Some(rank)

  override def getCardNameAsString: String = {
    var s = "(Spades," + rankList(rank) + ")"
    s
  }
}

private class Club(rank: Integer) extends Card {
  override def getSuit: String = "Club"
  override def getValue: Integer = valueForCard.apply(rank)
  override def getCardName: (String, String) = ("Club", rankList(rank))
  
  override def placeInList: Option[Integer] = Some(rank)

  override def getCardNameAsString: String = {
    var s = "(Club," + rankList(rank) + ")"
    s
  }
}

 class Joker() extends Card {
  var rank = 15
  var suit = "Joker"
  override def getSuit: String = suit
  override def getValue: Integer = valueForCard.apply(rank)
  override def getCardName: (String, String) = ("Joker", "")
  def setValue(value: String): Unit = {
     for(x <- 0 to (rankList.size - 1))
      if(value.equals(rankList(x)))
        rank = x
      end if
  }
  def setSuit(s: String): Unit = this.suit = s
  override def placeInList: Option[Integer] = Some(rank)

  override def getCardNameAsString: String = {
    var s = "(Joker, )"
    s
  }
}

private class EmptyCard() extends Card {
  override def getSuit: String = ""
  override def getValue: Integer = 0
  override def getCardName: (String, String) = ("", "")
  override def placeInList: Option[Integer] = None

  override def getCardNameAsString: String = "(,)"
  
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