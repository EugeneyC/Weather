package com.skrebtsov.eugeney.weather.model.models.secondapi


data class Night(
    var tempMin: Float,
    var tempMax: Float,
    var tempAvg: Float,
    var feelsLike: Float,
    var icon: String,
    var condition: String,
    var daytime: String,
    var polar: Boolean,
    var windSpeed: Double,
    var windGust: Float,
    var windDir: String,
    var pressureMm: Float,
    var pressurePa: Float,
    var humidity: Float,
    var precMm: Float,
    var precType: Float,
    var precStrength: Float,
    var cloudness: Double
)