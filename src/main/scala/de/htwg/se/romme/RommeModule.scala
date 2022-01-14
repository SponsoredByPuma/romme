package de.htwg.se.romme

import com.google.inject.{AbstractModule, Guice, Inject}
import com.google.inject.TypeLiteral

import de.htwg.se.romme.model.modelComponent.gameComponent.GameInterface
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl._

import de.htwg.se.romme.model.modelComponent.dropsComponent.DropsInterface
import de.htwg.se.romme.model.modelComponent.dropsComponent.dropsBaseImpl.Drops

import de.htwg.se.romme.controller.controllerComponent.ControllerInterface
import de.htwg.se.romme.controller.controllerComponent.controllerBaseImpl._

class RommeModule extends AbstractModule {
  override def configure(): Unit = {
    bind[ControllerInterface](new TypeLiteral[ControllerInterface]() {})
      .to(classOf[Controller])
    bind[GameInterface](new TypeLiteral[GameInterface]() {}).to(classOf[Game])
    // bind[DropsInterface](new TypeLiteral[DropsInterface]() {})
    //  .to(classOf[Drops])
  }
}
