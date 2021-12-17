package de.htwg.se.romme.model.modelComponent

import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.modelComponent.gameBaseImpl.Card

trait DropsInterface:

    def strategy(numberOfStrategy: Integer) : Integer
    def strategySameSuit: Integer
    def strategyOrder: Integer
    def execute(cards: ListBuffer[Card], numberOfStrategy: Integer, hasJoker: Boolean) : Integer