package model
//
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class KartenSpec extends AnyWordSpec {

  "A Karte" when {
    "Karo, Acht" should {
      "start with Karo, Acht" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.cardAsString() should startWith("Karo")
      }
      "end with Acht" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.cardAsString() should endWith("Acht")
      }
      "be Karo, Acht" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.cardAsString() should be("Karo, Acht")
      }
      "swap its Blatt to Herz" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.changeBlatt(Blatt.Herz)
        karoAcht.art should be(Blatt.Herz)
      }
      "swap its value to Neun" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.changeZiffer(Ziffer.Neun)
        karoAcht.wert should be(Ziffer.Neun)
      }
      "have Karo as Blatt" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.art should be(Blatt.Karo)
      }
      "have Acht as Value" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.wert should be(Ziffer.Acht)
      }
    }
  }
}
