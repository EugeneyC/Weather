package com.skrebtsov.eugeney.weather.model.modelObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class WeatherModelResponce {
    @SerializedName("coord")
    @Expose
    private var coord: Coord? = null

    @SerializedName("weather")
    @Expose
    private var weather: List<Weather?>? = null

    @SerializedName("base")
    @Expose
    private var base: String? = null

    @SerializedName("main")
    @Expose
    private var main: Main? = null

    @SerializedName("visibility")
    @Expose
    private var visibility: Int? = null

    @SerializedName("wind")
    @Expose
    private var wind: Wind? = null

    @SerializedName("clouds")
    @Expose
    private var clouds: Clouds? = null

    @SerializedName("dt")
    @Expose
    private var dt: Int? = null

    @SerializedName("sys")
    @Expose
    private var sys: Sys? = null

    @SerializedName("timezone")
    @Expose
    private var timezone: Int? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("cod")
    @Expose
    private var cod: Int? = null

    fun getCoord(): Coord? {
        return coord
    }

    fun setCoord(coord: Coord?) {
        this.coord = coord
    }

    fun getWeather(): List<Weather?>? {
        return weather
    }

    fun setWeather(weather: List<Weather?>?) {
        this.weather = weather
    }

    fun getBase(): String? {
        return base
    }

    fun setBase(base: String?) {
        this.base = base
    }

    fun getMain(): Main? {
        return main
    }

    fun setMain(main: Main?) {
        this.main = main
    }

    fun getVisibility(): Int? {
        return visibility
    }

    fun setVisibility(visibility: Int?) {
        this.visibility = visibility
    }

    fun getWind(): Wind? {
        return wind
    }

    fun setWind(wind: Wind?) {
        this.wind = wind
    }

    fun getClouds(): Clouds? {
        return clouds
    }

    fun setClouds(clouds: Clouds?) {
        this.clouds = clouds
    }

    fun getDt(): Int? {
        return dt
    }

    fun setDt(dt: Int?) {
        this.dt = dt
    }

    fun getSys(): Sys? {
        return sys
    }

    fun setSys(sys: Sys?) {
        this.sys = sys
    }

    fun getTimezone(): Int? {
        return timezone
    }

    fun setTimezone(timezone: Int?) {
        this.timezone = timezone
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCod(): Int? {
        return cod
    }

    fun setCod(cod: Int?) {
        this.cod = cod
    }
}