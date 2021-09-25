package com.skrebtsov.eugeney.weather.model.modules

import com.skrebtsov.eugeney.weather.model.WeatherApiOne
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModuleOpenweathermap {
    @Provides
    fun create(): WeatherApiOne {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()
        return retrofit.create(WeatherApiOne::class.java)
    }
}