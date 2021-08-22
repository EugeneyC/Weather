package com.skrebtsov.eugeney.weather.model.modelObject

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Main {

    @SerializedName("temp")
    @Expose
    private var temp: Double? = null

    @SerializedName("feels_like")
    @Expose
    private var feelsLike: Double? = null

    @SerializedName("temp_min")
    @Expose
    private var tempMin: Double? = null

    @SerializedName("temp_max")
    @Expose
    private var tempMax: Double? = null

    @SerializedName("pressure")
    @Expose
    private var pressure: Int? = null

    @SerializedName("humidity")
    @Expose
    private var humidity: Int? = null

    fun getTemp(): Double? {
        return temp
    }

    fun setTemp(temp: Double?) {
        this.temp = temp
    }

    fun getFeelsLike(): Double? {
        return feelsLike
    }

    fun setFeelsLike(feelsLike: Double?) {
        this.feelsLike = feelsLike
    }

    fun getTempMin(): Double? {
        return tempMin
    }

    fun setTempMin(tempMin: Double?) {
        this.tempMin = tempMin
    }

    fun getTempMax(): Double? {
        return tempMax
    }

    fun setTempMax(tempMax: Double?) {
        this.tempMax = tempMax
    }

    fun getPressure(): Int? {
        return pressure
    }

    fun setPressure(pressure: Int?) {
        this.pressure = pressure
    }

    fun getHumidity(): Int? {
        return humidity
    }

    fun setHumidity(humidity: Int?) {
        this.humidity = humidity
    }
}
