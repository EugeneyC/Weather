package com.skrebtsov.eugeney.weather.model.models.secondapi

data class Fact(
    var temp: Float,
    var feelsLike: Float,
    var icon: String,
    var condition: String,
    var windSpeed: Float,
    var windGust: Double,
    var windDir: String,
    var pressureMm: Float,
    var pressurePa: Float,
    var humidity: Float,
    var daytime: String,
    var polar: Boolean,
    var season: String,
    var precType: Float,
    var precStrength: Double,
    var isThunder: Boolean,
    var cloudness: Float,
    var obsTime: Float,
    var phenomIcon: String,
    var phenomCondition: String
)



