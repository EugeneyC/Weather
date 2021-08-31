package com.skrebtsov.eugeney.weather.model.models.secondapi


data class WeatherYandexResponse(
    var now: Float,
    var nowDt: String,
    var info: Info,
    var fact: Fact,
    var forecasts: List<Forecast?>
)