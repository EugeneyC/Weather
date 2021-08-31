package com.skrebtsov.eugeney.weather.model.models.secondapi


data class Hour(
    var hour: String,
    var hourTs: Float,
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
    var precMm: Double,
    var precPeriod: Float,
    var precType: Float,
    var precStrength: Double,
    var isThunder: Boolean,
    var cloudness: Float
)