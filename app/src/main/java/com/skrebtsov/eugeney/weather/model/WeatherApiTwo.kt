package com.skrebtsov.eugeney.weather.model

import com.skrebtsov.eugeney.weather.model.models.secondapi.WeatherYandexResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//53.902284, 27.561831
//GET https://api.weather.yandex.ru/v2/forecast?
interface WeatherApiTwo {


    @GET("forecast?")
    fun weatherYandex(
        @Header("X-Yandex-API-Key") consumerKey: String = "50a891de-b944-40c3-bd19-6370a4b78896",
        @Query("lat") lat: String = "53.902284",
        @Query("lon") lon: String = "27.561831",
        @Query("lang") lang: String = "eu-us",
        @Query("limit") limit: String = "1",
        @Query("hours") hours: String = "false",
        @Query("extra") extra: String = "false"
    ): Observable<WeatherYandexResponse>

    companion object {
        var BASE_URL = "https://api.weather.yandex.ru/v2/"

        fun create(): WeatherApiTwo {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(WeatherApiTwo::class.java)
        }
    }
}