package com.skrebtsov.eugeney.weather.model.modelObject


data class Main(
    var temp: Double? = null, var feelsLike: Double? = null,
    var tempMin: Double? = null, val tempMax: Double? = null,
    var pressure: Int? = null, var humidity: Int? = null
)