package state

import machine.IGumballMachinePrivate
import java.io.PrintStream

class SoldOutState(
    private val machine: IGumballMachinePrivate,
    private val output: PrintStream = System.out
) : IState {

    override fun insertQuarter() = output.println("You can't insert a quarter, the machine is sold out")

    override fun ejectQuarter() {
        if (machine.getCountQuarters() != 0) {
            output.println("${machine.getCountQuarters()} quarters returned")
            machine.removeAllQuarters()
        } else {
            output.println("You can't eject, you haven't inserted a quarter yet")
        }
    }

    override fun turnCrank() = output.println("You turned but there's no gumballs")

    override fun dispense() = output.println("No gumball dispensed")

    override fun fillMachine(ballsCount: Int) {
        machine.fillMachine(ballsCount)
        if (machine.getBallCount() > 0) {
            if (machine.getCountQuarters() > 0) {
                machine.setHasQuarterState()
            } else {
                machine.setNoQuarterState()
            }
        }
        output.println("You've filled machine with: $ballsCount balls")
    }

    override fun toString() = "sold out"
}