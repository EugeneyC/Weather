package com.skrebtsov.eugeney.weather.model.modules

import com.skrebtsov.eugeney.weather.model.WeatherApiTwo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModuleYandex {
    @Provides
    fun create(): WeatherApiTwo {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://api.weather.yandex.ru/v2/")
            .build()
        return retrofit.create(WeatherApiTwo::class.java)
    }
}