package de.htwg.se.romme

package model.modelComponent.gameComponent.gameBaseImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class StateSpec extends AnyWordSpec {
  "A State " when {
    "created " should {
      var c = StateContext()
      "be StateOutsideFalse" in {
        c.getState() should be(c.mystate)
      }
      "return false as it is in State False" in {
        c.getStateB() should be(false)
        c.outside() should be(false)
      }
      "be different when set to another State" in {
        var store = c.getState()
        c.setState(StateOutSideTrue())
        c.getState() should not be (store)
      }
      "return true as it is in its State true" in {
        c.getStateB() should be(true)
        c.outside() should be(true)
      }
    }
  }
}
