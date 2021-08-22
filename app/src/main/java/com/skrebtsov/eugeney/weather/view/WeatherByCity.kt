package com.skrebtsov.eugeney.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skrebtsov.eugeney.weather.R

class WeatherByCity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_by_city)
    }
}