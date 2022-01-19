package de.htwg.se.romme

import de.htwg.se.romme.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.romme.model.modelComponent.gameComponent.GameInterface
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl._
import de.htwg.se.romme.aview.gui.SwingGui
import de.htwg.se.romme.aview.Tui
import scala.io.StdIn.readLine

import com.google.inject.Guice
import de.htwg.se.romme.controller.controllerComponent.ControllerInterface

object Romme {
  val injector = Guice.createInjector(new RommeModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  def main(args: Array[String]): Unit = {
    var input: String = ""
    input = readLine()
    while (input != "quit") {
      tui.processInputReadLine(input)
      input = readLine()
    }
  }
}
