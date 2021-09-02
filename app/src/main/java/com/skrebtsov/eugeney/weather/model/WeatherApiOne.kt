package com.skrebtsov.eugeney.weather.model

import com.skrebtsov.eugeney.weather.Constans
import com.skrebtsov.eugeney.weather.model.models.firstapi.WeatherOpenWeathermapResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiOne {
    @GET("weather?")
    fun getWeatherInCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = Constans.API_KEY,
        @Query("units") units: String = "metric"
    ): Observable<WeatherOpenWeathermapResponse>

    companion object {

        var BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(): WeatherApiOne {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(WeatherApiOne::class.java)
        }
    }
}