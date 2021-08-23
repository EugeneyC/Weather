package com.skrebtsov.eugeney.weather.model.modelObject


data class Sys(
    var type: Int? = null, var id: Int? = null, var message: Double? = null,
    var country: String? = null, var sunrise: Int? = null, var sunset: Int? = null
)
