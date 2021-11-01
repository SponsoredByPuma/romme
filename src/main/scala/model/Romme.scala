package model
import scala.io.StdIn.readLine

trait global {
    var cardAmount = 0
    var yourTurn = true
    var victory = false


    enum Suit:
        case Heart, Diamond, Club, Spades // Herz, Karo, Kreuz , Pik
    enum Numbers:
        case two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace // 1,2,3,4,5,6,7,8,9,10, Bube, Dame, König, Ass
}

object Romme extends global {

    case class Cards(suit: Suit, number: Numbers) {



    }

    case class deck() {

        def newDeck(): Unit = {
          //  List[String] list = List(Suit.Heart, Numbers.nine)
            
        }

    }

    case class playerAction() {

        def checkVictory(): Unit = { // Methode die prüft ob man das Spiel gewonnen hat
          if (cardAmount == 0)
            victory = true
          end if
        }

        def gameStart(): Integer = { // Methode die jedem Spieler am Anfang genau 13 Karten austeilt
            println("You have 13 cards !")
            var s = 13
            return s
        }

        def pickUpACard(): Unit = { // Methode um eine Karte am Anfang des Zuges aufzuheben
            println("You have picked up a card !")
            cardAmount = cardAmount + 1
            println("You have now " + cardAmount + " cards on your hand.")
        }

        def placeCards(): Unit = { //Methode um Karten abzulegen, sei es nun anlegen oder eine neue Reihe zu erstellen
            println("How many cards would you like to drop off ?")
            var dropOff = readLine()
            val dropOffasInteger = dropOff.toInt
            var restCards = cardAmount - dropOffasInteger

            while (restCards < 1)
                println("Error you cant drop your amount of cards off. Please enter a new amount !")
                var dropOff2 = readLine()
                var dropOffError = dropOff2.toInt
                restCards = cardAmount - dropOffError
            cardAmount = restCards
            println("Your current card amount is: " + cardAmount)
        }

        def dropSingleCard(): Unit = { // Methode, die aufgerufen wird wenn man den Zug beenden möchte, da man immer eine Karte ablegen muss
            println("You need to drop one card !")
            cardAmount = cardAmount - 1
            println("Your current card amount is: " + cardAmount)
        }

    }

    def main(args: Array[String]) = {

    println("Welcome to Romme !")
    print("Please enter your name: ")
    val firstName = readLine()
    val pA = new playerAction()
    cardAmount = pA.gameStart()

    while (victory == false) 
        if (yourTurn == true)
            pA.pickUpACard()
            pA.placeCards()
            pA.dropSingleCard()
        end if
        pA.checkVictory()
    println("Congratulations you won !")    
  }
}
