package com.app.component.tea

import com.app.DoubleUtils
import com.app.beverage.Beverage
import com.app.beverage.Portion
import com.app.beverage.Size

class Tea(
    val teaType: TeaType,
    size: Size = Size.MIDDLE,
    portion: Portion = Portion.STANDARD
) : Beverage(size, portion) {

    override fun description(): String = "$portion $teaType Tea"

    override fun cost(): Double = DoubleUtils.roundDouble(
        teaType.getCost() + when (size) {
            Size.SMALL -> 0.69
            Size.MIDDLE -> 0.82
            Size.BIG -> 1.10
        }
    )
}