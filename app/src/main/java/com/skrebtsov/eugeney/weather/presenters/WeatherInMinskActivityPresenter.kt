package com.skrebtsov.eugeney.weather.presenters

import com.skrebtsov.eugeney.weather.model.WeatherApi
import com.skrebtsov.eugeney.weather.view.ContractWeatherInMinsk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class WeatherInMinskActivityPresenter: MvpPresenter<ContractWeatherInMinsk>() {
    private var CITY = "Minsk"

    fun getWeatherInMinsk(){
        WeatherApi.create().getWeatherInCity(CITY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.showWeather(it)
            }
    }
}