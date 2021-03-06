import display.CurrentConditionsDisplay
import display.ForecastDisplay
import display.HeatIndexDisplay
import display.statistic.HumidityStaticsDisplay
import display.statistic.PressureStatisticsDisplay
import display.statistic.TemperatureStatisticsDisplay
import subject.WeatherData
import util.WeatherMeasurement

fun main() {
    val weatherData = WeatherData()

    val currentConditionsDisplay = CurrentConditionsDisplay(weatherData)
    val temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherData)
    val humidityStaticsDisplay = HumidityStaticsDisplay(weatherData)
    val pressureStatisticsDisplay = PressureStatisticsDisplay(weatherData)
    val forecastDisplay = ForecastDisplay(weatherData)
    val heatIndexDisplay = HeatIndexDisplay(weatherData)

    weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.0))
    println()
    weatherData.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
    println()
    weatherData.setContext(WeatherMeasurement(78.0, 90.0, 29.2))
}