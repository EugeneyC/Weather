package com.skrebtsov.eugeney.weather.model.modelObject

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Sys {

    @SerializedName("type")
    @Expose
    private var type: Int? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("message")
    @Expose
    private var message: Double? = null

    @SerializedName("country")
    @Expose
    private var country: String? = null

    @SerializedName("sunrise")
    @Expose
    private var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    private var sunset: Int? = null

    fun getType(): Int? {
        return type
    }

    fun setType(type: Int?) {
        this.type = type
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getMessage(): Double? {
        return message
    }

    fun setMessage(message: Double?) {
        this.message = message
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getSunrise(): Int? {
        return sunrise
    }

    fun setSunrise(sunrise: Int?) {
        this.sunrise = sunrise
    }

    fun getSunset(): Int? {
        return sunset
    }

    fun setSunset(sunset: Int?) {
        this.sunset = sunset
    }
}
