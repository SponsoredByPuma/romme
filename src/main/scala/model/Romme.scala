package model
import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer


trait global {
    var cardAmount = 0
    var yourTurn = true
    var droppedCardsAlready = false // Um zu überprüfen, ob man schon draußen ist
    var victory = false


    enum Suit:
        case Heart, Diamond, Club, Spades // Herz, Karo, Kreuz , Pik
    enum Rank:
        case two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace // 1,2,3,4,5,6,7,8,9,10, Bube, Dame, König, Ass
}

object Romme extends global {

    case class Cards(suit: Suit, number: Rank) { // Klasse für die Karten die es in dem Spiel gibt

        def printing(): Unit = {
            println(this.suit.toString + ", " + this.number.toString)
        }
        


    }

    case class playerHands(d: deck) { // Die Karten die man auf der Hand hat

        var playerOneCard: Array[Cards] = new Array[Cards] (14) // Ein Cards-Array, das 14 Groß ist, da man am jeweils 13 Karten hat + 1 aufnimmt

        def startingTheGame(): Unit = {
            var i = 0
            for (i <- 1 to 13)
                playerOneCard(i) = d.drawFromDeck()
                playerOneCard(i).printing()
        }
    }

    case class deck() { // Klasse, für das ganze Deck

        var deck: ListBuffer[Cards] = ListBuffer() // Liste voller Karten

        def newDeck(): Unit = {
            var i = 0
            for (d <- Suit.values) // für jede Suit
                for (e <- Rank.values) // für jeden Rank
                    deck :+ new Cards(d,e) // adde in das Kartendeck die jeweilige Karte
            
        }

        def drawFromDeck(): Cards = {
            val random = scala.util.Random
            val pickACard = random.nextInt(110) // zufälliger Wert zwischen 0 und 109
            print(pickACard)
            val m = deck(pickACard) //Speichere die gezogene Karte
            deck.remove(pickACard) // lösche die gezogene Karte aus dem Deck 
            return m // gebe die gezogene Karte dem Spieler auf die Hand
        }

    }

    case class playerAction() { // für die Logik eine eigene Klasse

        def gameStartForTesting(): Integer = { // Methode, um zu überprüfen, ob checkVictory() funktioniert
            return 0;
        }


        def checkVictory(): Boolean = { // Methode die prüft ob man das Spiel gewonnen hat
          if (cardAmount == 0)
            return true
          end if
          return false
        }

        def gameStart(): Integer = { // Methode die jedem Spieler am Anfang genau 13 Karten austeilt
            println("You have 13 cards !")
            var s = 13
            return s
        }

        def pickUpACard(): Integer = { // Methode um eine Karte am Anfang des Zuges aufzuheben
            println("You have picked up a card !")
            //cardAmount = cardAmount + 1 // removed for testing
            println("You have now " + cardAmount + 1 + " cards on your hand.") // added + 1 since it is needed for the testing
            return cardAmount + 1 // for testing pourpose
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

/*
        val d = new deck()
        d.newDeck()
        val pH = new playerHands(d)
        pH.startingTheGame()
*/
        println("Welcome to Romme !")
        print("Please enter your name: ")
        val firstName = readLine()
        val pA = new playerAction()
        cardAmount = pA.gameStart()
        

        while (victory == false) 
            if (yourTurn == true)
                cardAmount = pA.pickUpACard() // for testing pourpose in before it was pA.pickUpACard()
                pA.placeCards()
                pA.dropSingleCard()
            end if
            victory = pA.checkVictory()
        println("Congratulations you won !")    
    }
}
