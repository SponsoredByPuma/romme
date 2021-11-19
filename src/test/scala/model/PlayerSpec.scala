package de.htwg.se.romme

package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
  "A Player " when {
    "typing his name" should {
      val player = Player("Stefan")
      "be Stefan" in {
        player.toString should be("Stefan")
      }
    }
  }
}
