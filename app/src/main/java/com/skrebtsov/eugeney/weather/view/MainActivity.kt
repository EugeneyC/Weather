package com.skrebtsov.eugeney.weather.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.RxView
import com.skrebtsov.eugeney.weather.databinding.ActivityMainBinding
import io.reactivex.disposables.CompositeDisposable
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        RxView.clicks(binding.btnWeatherInMinsk)
            .subscribe{
                startActivityWeatherInMinsk()
            }

        RxView.clicks(binding.btnWeatherByCity)
            .subscribe{
                startActivityWeatherByCity()
            }
    }

    private fun startActivityWeatherByCity() {
        val intent = Intent(this, WeatherByCity::class.java)
        startActivity(intent)
    }

    private fun startActivityWeatherInMinsk() {
        val intent = Intent(this, WeatherInMinsk::class.java)
        startActivity(intent)
    }

}