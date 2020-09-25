package ir.kaaveh.urmanopenweather.repository

import androidx.lifecycle.LiveData
import ir.kaaveh.urmanopenweather.Config
import ir.kaaveh.urmanopenweather.model.WeatherResponse
import ir.kaaveh.urmanopenweather.repository.db.WeatherDao
import ir.kaaveh.urmanopenweather.repository.network.WeatherNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherRepository(
    private val weatherDao: WeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) {
    init {
        weatherNetworkDataSource.downloadedWeather.observeForever { newWeather ->
            persistFetchedWeather(newWeather)
        }
    }

    suspend fun getCurrentWeather(): LiveData<WeatherResponse> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            weatherDao.getWeather()
        }
    }

    private suspend fun initWeatherData() {
        if (Config.isFirstRequest) {
            weatherNetworkDataSource.fetchWeather()
            Config.isFirstRequest = false
        }
    }

    private fun persistFetchedWeather(newWeather: WeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            weatherDao.upsert(newWeather)
        }
    }
}