package de.htwg.se.romme
package model

case class Player(name: String) {
  override def toString: String = name
}

/*case class Player(name: String, hands: PlayerHands, deck: Deck, table: Table) {
  override def toString: String = name

  def startTheGame(): Unit = {
    hands.draw13Cards(deck)
  }

  def drop1Card(idx: Integer): Unit = {
    hands.dropASingleCard(idx)
  }

  def sortCards(): Unit = {
    hands.sortMyCards()
  }

  def show(): Unit = {
    hands.showYourCards()
  }

  def pickingGraveYard(): Unit = {
    hands.playerOneHand.addOne(table.graveYard)
  }

}
 */
