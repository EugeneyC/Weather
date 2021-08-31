package com.skrebtsov.eugeney.weather.model.models.secondapi


data class NightShort(
    var temp: Float,
    var feelsLike: Float,
    var icon: String,
    var condition: String,
    var windSpeed: Double,
    var windGust: Double,
    var windDir: String,
    var pressureMm: Float,
    var pressurePa: Float,
    var humidity: Float,
    var precType: Float,
    var precStrength: Float,
    var cloudness: Double
)