package com.skrebtsov.eugeney.weather.presenters

import com.skrebtsov.eugeney.weather.model.WeatherApi
import com.skrebtsov.eugeney.weather.view.ContractWeatherByCity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class WeatherInCityActivityPresenter: MvpPresenter<ContractWeatherByCity>() {

    fun getWeatherByCity(city:String){
        WeatherApi.create().getWeatherInCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.showWeather(it)
            }
    }
}