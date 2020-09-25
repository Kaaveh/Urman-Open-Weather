package ir.kaaveh.urmanopenweather.repository

import android.util.Log
import ir.kaaveh.urmanopenweather.model.WeatherResponse
import ir.kaaveh.urmanopenweather.repository.network.MyAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun getWeather(): WeatherResponse? {
    var response: Response<WeatherResponse>
    withContext(Dispatchers.IO) {
        response = MyAPI().getWeather()
        if (response.isSuccessful)
            Log.v("getWeather()", "response.isSuccessful is true")
    }
    return response.body()
}