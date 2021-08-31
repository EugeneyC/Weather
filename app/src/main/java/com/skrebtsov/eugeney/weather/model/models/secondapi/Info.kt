package com.skrebtsov.eugeney.weather.model.models.secondapi


data class Info(
    var lat: Double,
    var lon: Double,
    var tzinfo: Tzinfo,
    var defPressureMm: Float,
    var defPressurePa: Float,
    var url: String
)