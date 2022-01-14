package de.htwg.se.romme.model.modelComponent.dropsComponent.dropsMockImpl

import de.htwg.se.romme.model.modelComponent.dropsComponent.DropsInterface
import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Card

abstract class Drops() extends DropsInterface {
  def strategy(numberOfStrategy: Integer): Integer = 0
  def strategySameSuit: Integer = 0
  def strategyOrder: Integer = 0
  def execute(
      cards: ListBuffer[Card],
      numberOfStrategy: Integer,
      hasJoker: Boolean
  ): Integer = 0
}
