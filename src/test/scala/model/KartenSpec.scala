package model
//
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class KartenSpec extends AnyWordSpec {

  "A Karte" when {
    "Karo, Acht" should {
      "show Karo, Acht" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.showCard should be(Karo, Acht)
      }
      "swap its Blatt to Herz" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.changeBlatt(Blatt.Herz)
        karoAcht.art should be(Herz)
      }
      "have Karo as Blatt" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.art shouldBe a[Karo]
      }
      "have Acht as Value" in {
        val karoAcht = new Karte(Blatt.Karo, Ziffer.Acht)
        karoAcht.wert shouldBe a[Acht]
      }
    }
  }
}
