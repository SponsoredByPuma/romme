/*
import scala.reflect.internal.pickling.PickleFormat
1 + 2
val x = 3
def f(x: Int) = x + 1
f(7)
val l = List(1, 2, 3)
l.length

val romme = """romme"""

val oben = "+---+---+"
val mitte = """
|   |   |
|   |   |
|   |   |
"""
val unten = "+---+---+"

val plus = "+"
val minus = "-"

val zusammen = plus + minus * 3 + plus + minus * 3 + plus

def testfunktion(breite: Int) = minus * breite

val test = testfunktion(4)
print(test)

val stern = "*"

val drei = 3

case class Karten(name: String)
val karte = Karten("Karo-Ass")
karte.name
val KartenListe = List(karte)

enum Zahlen:
    case Bube, Dame, König, Ass , Zwei, Drei, Vier, Fünf, Sechs, Sieben, Acht, Neun, Zehn

enum Arten:
    case Herz, Karo, Kreuz, Pick


case class testKarten(var a: Arten,var z: Zahlen):
    def zeigeKarte() = print(a,z)
    def zeigeKarteAlsString() : Unit = {
        var s:String = this.a.toString +", " + this.z.toString
    }
    def neueZahl(nz: Zahlen): testKarten = {
        this.z = nz
        return this
    }
    def neueArt(na: Arten): testKarten = {
        this.a = na
        return this
    }

val test3 = new testKarten(Arten.Karo,Zahlen.Acht)

print(test3.zeigeKarteAlsString())
test3.zeigeKarte()
test3.neueArt(Arten.Herz)
test3.neueZahl(Zahlen.Bube)

print(test3.a)
print(test3.z)

/*case class herzkarten(kartenArt: Herz):
    def zeigeKarte() = print(this.kartenArt)



val k = herzkarten(Herz.Acht)
k.zeigeKarte()
 */
 */
import scala.collection.mutable.ListBuffer

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
class Deck() {

  var deckList: ListBuffer[Card] = ListBuffer()

  def createNewDeck(): Unit = {

    var suitCounter = 0
    var rankCounter = 0

    for (suitCounter <- 0 to 3) {
      for (rankCounter <- 0 to 12) {
        val c = new Card(suitCounter, rankCounter)
        deckList :+ c // add every Card twice
        deckList :+ c
      }
    }

    var jokerCounter = 0
    for (jokerCounter <- 1 to 6) {
      val jokerCard = new Card(4, 13)
      deckList :+ jokerCard
    }
    print(deckList.length)
  }

  def drawFromDeck(): Card = {
    val random = new scala.util.Random // random generator
    // safe a random value between 0 and the size of the current deck - 1
    val tmp = random.nextInt(deckList.length - 1)
    // safe the card which will be drawn from the deck
    val tmpsafe = deckList(tmp)
    deckList.remove(tmp) // remove the card from the game
    return tmpsafe // return the card
  }

}

var d = new Deck()

var deckLiest: ListBuffer[Integer] = ListBuffer()

var i = 0
for (i <- 0 to 100)
  deckLiest.addOne(i)
print(deckLiest.size)
