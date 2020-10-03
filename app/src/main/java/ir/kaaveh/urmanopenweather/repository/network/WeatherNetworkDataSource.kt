package ir.kaaveh.urmanopenweather.repository.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.kaaveh.urmanopenweather.model.WeatherResponse
import java.io.IOException

class WeatherNetworkDataSource {
    private val _downloadedWeather = MutableLiveData<WeatherResponse>()
    val downloadedWeather: LiveData<WeatherResponse>
        get() = _downloadedWeather

    suspend fun fetchWeather() {
        try {
            val fetchedWeathers = MyAPI()
                .getWeather()
            _downloadedWeather.postValue(fetchedWeathers)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}