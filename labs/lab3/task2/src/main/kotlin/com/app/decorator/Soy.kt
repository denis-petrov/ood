package com.app.decorator

import com.app.DoubleUtils
import com.app.beverage.Beverage
import com.app.beverage.Size

class Soy(private val beverage: Beverage) : CondimentDecorator(beverage) {

    override var description: String = beverage.description + ", Soy"

    override fun cost(): Double {
        val cost = beverage.cost()
        return DoubleUtils.roundDouble(
            when (beverage.size) {
                Size.SMALL -> cost + 0.10
                Size.MIDDLE -> cost + 0.15
                Size.BIG -> cost + 0.20
            }
        )
    }
}