package de.htwg.se.romme.model.modelComponent.fileIOComponent
import de.htwg.se.romme.model.modelComponent.gameComponent._
import de.htwg.se.romme.model.modelComponent.gameComponent.gameBaseImpl.Card
import scala.collection.mutable.ListBuffer
import de.htwg.se.romme.model.modelComponent.fileIOComponent.fileIOXmlImpl.FileIO

trait FileIOInterface:
    def load: GameInterface
    def save(game: GameInterface): Unit

object FileIOInterface {
    def apply(): FileIOInterface = FileIO()
}