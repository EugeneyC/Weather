package com.skrebtsov.eugeney.weather.model.models.secondapi


data class Tzinfo(
    var offset: Float,
    var name: String,
    var abbr: String,
    var dst: Boolean
    )