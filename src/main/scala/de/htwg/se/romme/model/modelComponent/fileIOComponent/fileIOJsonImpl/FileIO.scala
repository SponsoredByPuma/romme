package de.htwg.se.romme.model.modelComponent.fileIOComponent.fileIOJsonImpl

import play.api.libs.json._
import java.io._
import scala.io.Source
import scala.collection.mutable.ListBuffer

import de.htwg.se.romme.model.modelComponent.fileIOComponent.FileIOInterface
import de.htwg.se.romme.model.modelComponent.gameComponent.GameInterface
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Card
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Table
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Deck
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Player
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.PlayerHands
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Game

class FileIO extends FileIOInterface {

  val suitForCard = Map(
    "Heart" -> 0,
    "Diamond" -> 1,
    "Club" -> 2,
    "Spades" -> 3,
    "Joker" -> 4,
    "" -> 5
  )

  val rankForCard = Map(
    "two" -> 0,
    "three" -> 1,
    "four" -> 2,
    "five" -> 3,
    "six" -> 4,
    "seven" -> 5,
    "eight" -> 6,
    "nine" -> 7,
    "ten" -> 8,
    "jack" -> 9,
    "queen" -> 10,
    "king" -> 11,
    "ace" -> 12
  )


  def getCard(s: String): Card = {
    if(s.equals("(,)"))
      return Card(5,0)
    end if
    val s1 = s.dropRight(1)
    val s2 = s1.substring(1)
    val s3 = s2.split(",")

    val zuit = suitForCard.apply(s3(0))
    if(zuit > 3)
      var car = Card(zuit,0)
      car
    else
      var car = Card(zuit, rankForCard.apply(s3(1)))
      car
    end if
  }

  override def load: GameInterface = {
    val source: String = Source.fromFile("game.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    var d = (json \ "game" \ "Deck").get
    var groesse = (d \ "groesse").get.as[Int]
    var d1k: List[Card] =
      (for (i <- 0 until groesse) yield {
        getCard( (d \\ "cardName")(i).as[String])
      }).toList

    var d1d: ListBuffer[Card] = ListBuffer()
    d1d.addAll(d1k)

    for(x <- d1d)
      print(x.getCardName)
    var dekk: Deck = Deck()
    dekk.deckList = d1d

    val t = (json \ "game" \ "Table").get
    var grav = (t \ "friedhof" \ "cardName").get.as[String]
    var yard = getCard(grav)
    print(yard.getCardName)
    var tAnzahl = (t \ "droppedCardsAnzahl").get.as[Int]
    var test = (t \ "droppedCards").get
    var count = 0
    var count2 = 0
    var tk: List[List[Card]] = {
      (for (i <-0 until tAnzahl) yield {
        var str = "size" + i.toString
        var big = (t \\ str)
        var big2 = big.toList
        var big3 = big2(0).as[Int]
        count = count + big3
        var ll: ListBuffer[Card] = ListBuffer()
        (for (x <- count2 until count) yield {
          println(x)
          ll.addOne(getCard( (test \\ "cardName")(x).as[String]))
        })
        count2 = count2 + big3
        ll.toList
      }).toList
    }
    var ts: ListBuffer[ListBuffer[Card]] = ListBuffer()
    for(x <- 0 until tk.size) yield {
      var tmpL: ListBuffer[Card] = ListBuffer()
      for(y <- tk(x)) yield {
        tmpL.addOne(y)
      }
      ts.addOne(tmpL)
    }

    var teible: de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Table = de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Table()
    teible.graveYard = yard
    teible.droppedCardsList = ts

    val p1 = (json \ "game" \ "player1").get
    val p1n = (p1 \ "name").get.as[String]
    val p1Anzahl = (p1 \ "anzahl").get.as[Int]
    var p1k: List[Card] =
      (for (i <- 0 until p1Anzahl) yield {
        getCard( (p1 \\ "cardName")(i).as[String])
      }).toList
    var p1c: ListBuffer[Card] = ListBuffer()
    p1c.addAll(p1k)
    var hands1: PlayerHands = PlayerHands(teible)
    hands1.playerOneHand = p1c
    var player1: Player = Player("Player 1", hands1, teible)

    var p2 = (json \ "game" \ "player2").get
    val p2n = (p2 \ "name").get.as[String]
    val p2Anzahl = (p2 \ "anzahl").get.as[Int]
    var p2k: List[Card] =
      (for (i <- 0 until p2Anzahl) yield {
        getCard( (p2 \\ "cardName")(i).as[String])
      }).toList
    var p2c: ListBuffer[Card] = ListBuffer()
    p2c.addAll(p2k)
    var hands2: PlayerHands = PlayerHands(teible)
    hands2.playerOneHand = p2c
    var player2: Player = Player("Player 2", hands2, teible)
    var game: Game = Game(teible,player1,player2,dekk)
    game
  }

  override def save(game: GameInterface): Unit = {
    val pw = new PrintWriter(new File("game.json"))
    pw.write(Json.prettyPrint(gameToJson(game)))
    pw.close
  }

  def gameToJson(game: GameInterface) = {
    Json.obj(
      "game" -> Json.obj(
        "player1" -> Json.obj(
          "name" -> game.player.name,
          "karten" -> vectorToJson(game.player.hands.playerOneHand),
          "anzahl" ->game.player.hands.playerOneHand.size
        ),
        "player2" -> Json.obj(
          "name" -> game.player2.name,
          "karten" -> vectorToJson(game.player2.hands.playerOneHand),
          "anzahl" -> game.player2.hands.playerOneHand.size
        ),
        "Table" -> Json.obj(
          "droppedCards" -> (for (x <- 0 until game.table.droppedCardsList.size) yield Json.obj(
          "new List" -> vectorToJson(game.table.droppedCardsList(x)),
          "size" + x.toString  -> game.table.droppedCardsList(x).size)),
          "droppedCardsAnzahl" -> game.table.droppedCardsList.size,
          "friedhof" -> karteToJson(game.table.graveYard)
        ),
        "Deck" -> Json.obj(
        "randomCards" -> vectorToJson(game.deck.deckList),
        "groesse" -> game.deck.deckList.size
        )
      )
    )
  }

  def listListToJson(entryList: ListBuffer[ListBuffer[Card]]): JsValue = {
        Json.toJson(
      for {
        i <- entryList
      } yield {
        vectorToJson(i)
      }
    )
  }

  def karteToJson(kaertle: Card) =
    Json.toJson(
      Json.obj(
        "cardName" -> kaertle.getCardNameAsString
      )
    )

  def vectorToJson(vec: ListBuffer[Card]) =
    Json.toJson(
      for {
        i <- vec
      } yield {
        Json.obj(
          "cardName" -> i.getCardNameAsString
        )
      }
    )

}
 
