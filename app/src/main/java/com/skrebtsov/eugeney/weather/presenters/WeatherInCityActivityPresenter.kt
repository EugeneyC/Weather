package com.skrebtsov.eugeney.weather.presenters

import com.skrebtsov.eugeney.weather.model.WeatherApi
import com.skrebtsov.eugeney.weather.model.modelObject.DataWeatherCity
import com.skrebtsov.eugeney.weather.model.modelObject.WeatherModelResponse
import com.skrebtsov.eugeney.weather.view.ContractWeatherByCity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class WeatherInCityActivityPresenter : MvpPresenter<ContractWeatherByCity>() {

    private val disposableBag = CompositeDisposable()

    fun getWeatherByCity(city: String) {
        val result = WeatherApi.create().getWeatherInCity(city)
            .map { parseDateWeatherCity(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showWeather(it)
            },
                {
                    viewState.showError()
                })
        disposableBag.add(result)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.clear()
    }

    private fun parseDateWeatherCity(weatherModelResponse: WeatherModelResponse): DataWeatherCity {
        return DataWeatherCity(
            nameCity = weatherModelResponse.name.toString(),
            tempInCity = weatherModelResponse.main?.temp.toString(),
            weatherInCity = weatherModelResponse.weather?.get(0)?.description.toString(),
            wind = weatherModelResponse.wind?.speed.toString()
        )
    }
}