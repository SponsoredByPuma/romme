package de.htwg.se.romme.util

trait Command {
  def doStep: Unit

  def undoStep: Unit

  def redoStep: Unit

}
