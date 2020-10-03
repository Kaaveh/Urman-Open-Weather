package ir.kaaveh.urmanopenweather.model

import ir.kaaveh.urmanopenweather.repository.network.WeatherNetworkDomain

data class WeatherResponse(
    val current: WeatherNetworkDomain,
    val hourly: List<WeatherNetworkDomain>
)