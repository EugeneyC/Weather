package com.skrebtsov.eugeney.weather.model.modelObject


data class Weather(
    var id: Int? = null, var main: String? = null, var description: String? = null,
    var icon: String? = null
)