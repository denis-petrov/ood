package machine

import state.*
import java.io.PrintStream

class MultiGumballMachineImpl(
    private var countBalls: Int = 0,
    private val output: PrintStream = System.out
) : IGumballMachinePrivate {

    private val soldState: IState = SoldState(this, output)
    private val soldOutState: IState = SoldOutState(this, output)
    private val noQuarterState: IState = NoQuarterState(this, output)
    private val hasQuarterState: IState = HasQuarterState(this, output)
    private var state: IState = soldOutState

    private var countQuarters: Int = 0

    init {
        if (countBalls > 0) {
            state = noQuarterState
        }
    }

    fun insertQuarter() = state.insertQuarter()

    fun ejectQuarter() = state.ejectQuarter()

    fun turnCrank() = state.turnCrank()

    fun dispense() = state.dispense()

    override fun setHasQuarterState() {
        state = hasQuarterState
    }

    override fun setNoQuarterState() {
        state = noQuarterState
    }

    override fun setSoldState() {
        state = soldState
    }

    override fun setSoldOutState() {
        state = soldOutState
    }

    override fun releaseBall() {
        if (countBalls > 0 && countQuarters > 0) {
            output.println("A gumball comes rolling out the slot...")
            --countBalls
            --countQuarters
        }
    }

    override fun getBallCount() = countBalls

    override fun getCountQuarters(): Int = countQuarters

    override fun addQuarter() {
        countQuarters++
    }

    override fun removeAllQuarters() {
        countQuarters = 0
    }

    override fun fillMachine(ballsCount: Int) {
        countBalls = ballsCount
        if (countBalls > 0) {
            setNoQuarterState()
        }
    }

    fun getState() = state
}