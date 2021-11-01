package model

enum Ziffer:
   case Zwei, Drei, Vier, Fünf, Sechs, Sieben, Acht, Neun, Zehn, Bube, Dame, König, Ass
enum Blatt:
    case Herz, Pick, Karo, Kreuz

case class Karte(var art: Blatt,var wert: Ziffer):
    //def this(zeichen: Blatt, value: Ziffer) = this(zeichen, value)
    //def showCard() = print(this.art, this.wert)
    // def changeBlatt(neuesBlatt: Blatt): Karte = copy(neuesBlatt, wert)
    //def changeZiffer(neuerWert: Ziffer): Karte = copy(art, neuerWert)
    def changeBlatt(newBlatt: Blatt): Karte = {
        this.art = newBlatt
        return this
    }
    def changeZiffer(newZiffer: Ziffer): Karte = {
        this.wert = newZiffer
        return this
    }
    def cardAsString() : String = {
        var s:String = this.art.toString +", " + this.wert.toString
        return s
    }


