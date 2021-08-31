package com.skrebtsov.eugeney.weather.model.models.secondapi


data class Evening(
    var tempMin: Float,
    var tempMax: Float,
    var tempAvg: Float,
    var feelsLike: Float,
    var icon: String,
    var condition: String,
    var daytime: String,
    var polar: Boolean,
    var windSpeed: Double,
    var windDir: String,
    var pressureMm: Float,
    var pressurePa: Float,
    var humidity: Float,
    var precMm: Double,
    var precPeriod: Float,
    var precType: Float,
    var precStrength: Double,
    var cloudness: Double
)
