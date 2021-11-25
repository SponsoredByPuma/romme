package de.htwg.se.romme

package model

trait State: // Interface State
    def outside(state_Context: StateContext): Boolean


class StateOutSideTrue() extends State {

    override def outside(state_Context: StateContext): Boolean = {
        println("You are outside !")
        val b = true
        return b
    }

}

class StateOutsideFalse() extends State {
    override def outside(state_Context: StateContext): Boolean = {
        println("You are not outside yet !")
        val s = false
        return s
    }
}


class StateContext() {
    var mystate: State = StateOutsideFalse()
    def setState(newState: State): Unit = {
        mystate = newState
    }

    def getState() : State = {
        return mystate
    }

    def getStateB() : Boolean = {
        return mystate.outside(this)
    }

    def outside(): Boolean = {
        val b = mystate.outside(this)
        return b
    }
}