package com.skrebtsov.eugeney.weather.model.models.secondapi


data class Forecast(
    var date: String,
    var dateTs: Float,
    var week: Float,
    var sunrise: String,
    var sunset: String,
    var moonCode: Float,
    var moonText: String,
    var parts: Parts,
    var hours: List<Hour?>
)
