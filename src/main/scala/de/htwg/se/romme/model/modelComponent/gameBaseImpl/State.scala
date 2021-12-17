package de.htwg.se.romme.model.modelComponent.gameBaseImpl

trait State: // Interface State
    def outside(state_Context: StateContext): Boolean


class StateOutSideTrue() extends State {

    override def outside(state_Context: StateContext): Boolean = {
        println("You are outside !")
        true
    }

}

class StateOutsideFalse() extends State {
    override def outside(state_Context: StateContext): Boolean = {
        println("You are not outside yet !")
        false
    }
}


class StateContext() :
    var mystate: State = StateOutsideFalse()
    def setState(newState: State): Unit  = mystate = newState
    def getState() : State = mystate
    def getStateB() : Boolean =  mystate.outside(this)
    def outside(): Boolean = mystate.outside(this)
        