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
