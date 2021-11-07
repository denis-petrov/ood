package state

import machine.IGumballMachine
import java.io.PrintStream

class NoQuarterState(
    private val machine: IGumballMachine,
    private val output: PrintStream = System.out
) : IState {

    override fun insertQuarter() {
        output.println("You inserted a quarter")
        machine.setHasQuarterState()
        machine.addQuarter()
    }

    override fun ejectQuarter() = output.println("You haven't inserted a quarter")

    override fun turnCrank() = output.println("You turned but there's no quarter")

    override fun dispense() = output.println("You need to pay first")

    override fun toString() = "waiting for quarter"
}