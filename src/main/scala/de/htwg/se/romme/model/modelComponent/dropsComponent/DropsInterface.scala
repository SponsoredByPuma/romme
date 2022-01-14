package de.htwg.se.romme.model.modelComponent.dropsComponent

import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Card

trait DropsInterface:

    def strategy(numberOfStrategy: Integer) : Integer
    def strategySameSuit: Integer
    def strategyOrder: Integer
    def execute(cards: ListBuffer[Card], numberOfStrategy: Integer, hasJoker: Boolean) : Integer