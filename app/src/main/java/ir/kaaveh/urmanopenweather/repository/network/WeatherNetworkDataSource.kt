package ir.kaaveh.urmanopenweather.repository.network

import android.util.Log
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
            val fetchedCurrentWeather = MyAPI()
                .getWeatherAsync()
                .await()
            _downloadedWeather.postValue(fetchedCurrentWeather)
        } catch (e: IOException) {
            Log.e("IOException", e.message)
        }
    }
}