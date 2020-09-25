package ir.kaaveh.urmanopenweather.repository.network

import ir.kaaveh.urmanopenweather.model.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyAPI {
    @GET("onecall?lat=37.5498&lon=45.0786&exclude=daily&appid=626702989faabae418f5dbe4db78dbab&units=metric&lang=fa")
    suspend fun getWeather():
            Response<WeatherResponse>

    companion object {
        operator fun invoke(): MyAPI {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .build()
                .create(MyAPI::class.java)
        }
    }
}