package ir.kaaveh.urmanopenweather.repository

import ir.kaaveh.urmanopenweather.repository.db.WeatherDatabase
import ir.kaaveh.urmanopenweather.repository.network.WeatherNetworkDataSource
import ir.kaaveh.urmanopenweather.repository.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val weatherDatabase: WeatherDatabase
) {
    var currentWeather = weatherDatabase.weatherDao.getCurrentWeather()
    var forecastWeathers = weatherDatabase.weatherDao.getAllWeatherForecast()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            weatherNetworkDataSource.fetchWeather()
        }

        weatherNetworkDataSource.downloadedWeather.observeForever { newResponse ->
            val newWeathers =
                listOf(newResponse.current.asDomainModel()) + newResponse.hourly.map { it.asDomainModel() }
            GlobalScope.launch(Dispatchers.IO) {
                weatherDatabase.weatherDao.insert(newWeathers)
            }
        }
    }
}