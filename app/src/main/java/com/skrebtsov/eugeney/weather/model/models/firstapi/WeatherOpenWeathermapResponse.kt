package com.skrebtsov.eugeney.weather.model.models.firstapi


data class WeatherOpenWeathermapResponse(
    var coord: Coord? = null, var weather: List<Weather?>? = null,
    var base: String? = null, var main: Main? = null, var visibility: Int? = null,
    var wind: Wind? = null, var clouds: Clouds? = null, var dt: Int? = null,
    var sys: Sys? = null, var timezone: Int? = null, var id: Int? = null,
    var name: String? = null, var cod: Int? = null
)