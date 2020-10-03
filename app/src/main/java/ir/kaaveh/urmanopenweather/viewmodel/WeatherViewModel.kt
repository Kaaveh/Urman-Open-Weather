package ir.kaaveh.urmanopenweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.kaaveh.urmanopenweather.model.Weather
import ir.kaaveh.urmanopenweather.repository.WeatherRepository

class WeatherViewModel(
    weatherRepository: WeatherRepository
) : ViewModel() {
    private var _currentWeather = MutableLiveData<Weather>()
    val currentWeather: LiveData<Weather>
        get() = _currentWeather

    private var _forecastWeather = MutableLiveData<List<Weather>>()
    val forecastWeather: LiveData<List<Weather>>
        get() = _forecastWeather

    init {
        weatherRepository.currentWeather.observeForever {
            _currentWeather.postValue(it)
        }
        weatherRepository.forecastWeathers.observeForever {
            _forecastWeather.postValue(it)
        }
    }
}