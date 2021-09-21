package display

import observer.Observer
import subject.Subject
import util.WeatherMeasurement
import kotlin.math.pow

class HeatIndexDisplay(
    observerSubject: Subject<WeatherMeasurement>,
    priority: Int = 0
) : Observer<WeatherMeasurement>, DisplayElement {

    private var heatIndex = 0.0

    init {
        observerSubject.registerObserver(this, priority, WeatherMeasurement().getFieldNames())
    }

    override fun update(context: WeatherMeasurement) {
        heatIndex = calculateHeatIndex(context.temperature, context.humidity)

        display()
    }

    override fun display() {
        println("Heat index is $heatIndex")
    }

    private fun calculateHeatIndex(temperature: Double, humidity: Double): Double {
        return (16.923 +
                1.85212e-1 * temperature +
                5.37941 * humidity -
                1.00254e-1 * temperature * humidity +
                9.41695e-3 * temperature.pow(2) +
                7.28898e-3 * humidity.pow(2) +
                3.45372e-4 * temperature.pow(2) * humidity -
                8.14971e-4 * temperature * humidity.pow(2) +
                1.02102e-5 * temperature.pow(2) * humidity.pow(2) -
                3.8646e-5 * temperature.pow(3) +
                2.91583e-5 * humidity.pow(3) +
                1.42721e-6 * temperature.pow(3) * humidity +
                1.97483e-7 * temperature * humidity.pow(3) -
                2.18429e-8 * temperature.pow(3) * humidity.pow(2) +
                8.43296e-10 * temperature.pow(2) * humidity.pow(3) -
                4.81975e-11 * temperature.pow(3) * humidity.pow(3))
    }
}