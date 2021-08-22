package com.skrebtsov.eugeney.weather.model.modelObject

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Coord {
    @SerializedName("lon")
    @Expose
    private var lon: Double? = null

    @SerializedName("lat")
    @Expose
    private var lat: Double? = null

    fun getLon(): Double? {
        return lon
    }

    fun setLon(lon: Double?) {
        this.lon = lon
    }

    fun getLat(): Double? {
        return lat
    }

    fun setLat(lat: Double?) {
        this.lat = lat
    }

}
