package com.skrebtsov.eugeney.weather.model.modelObject

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Clouds {
    @SerializedName("all")
    @Expose
    private var all: Int? = null

    fun getAll(): Int? {
        return all
    }

    fun setAll(all: Int?) {
        this.all = all
    }

}
