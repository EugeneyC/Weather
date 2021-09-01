package com.skrebtsov.eugeney.weather.presenters

import com.skrebtsov.eugeney.weather.model.WeatherApiOne
import com.skrebtsov.eugeney.weather.model.WeatherApiTwo
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import com.skrebtsov.eugeney.weather.model.models.firstapi.WeatherOpenWeathermapResponse
import com.skrebtsov.eugeney.weather.model.models.secondapi.WeatherYandexResponse
import com.skrebtsov.eugeney.weather.view.ContractWeatherByCity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

@InjectViewState
class WeatherInCityActivityPresenter : MvpPresenter<ContractWeatherByCity>() {
    private var CITY = "Minsk"
    private val disposableBag = CompositeDisposable()

    fun startAutoUpdate(){
        disposableBag.add(Observable.merge(
            getWeatherByCityAutoUpdate(CITY),
            getWeatherYandexByCityAutoUpdate()
        )
            .repeat()
            .timeInterval(TimeUnit.MINUTES)
            .flatMap {
                if (it.time() <= 1) {
                    Observable.just(it.value()).delay(1, TimeUnit.MINUTES)
                } else {
                    Observable.just(it.value())
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showWeather(it)
            },
                {
                    viewState.showError()
                }))

    }

    private fun getWeatherByCityAutoUpdate(city: String): Observable<DataWeatherCity>? {
        return WeatherApiOne.create().getWeatherInCity(city)
            .delay(3, TimeUnit.MINUTES)
            .map { parseDateWeatherCity(it) }
    }

    private fun getWeatherYandexByCityAutoUpdate(): Observable<DataWeatherCity> {
        return WeatherApiTwo.create().weatherYandex()
            .delay(5, TimeUnit.MINUTES)
            .map { parseDateWeatherYandexCity(it) }
    }

    fun getWeatherByCity(city: String){
        val result = WeatherApiOne.create().getWeatherInCity(city)
            .map { parseDateWeatherCity(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showWeather(it)
            },{
                viewState.showError()
            })
        disposableBag.add(result)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.dispose()
        disposableBag.clear()
    }

    private fun parseDateWeatherCity(weatherOpenWeathermapResponse: WeatherOpenWeathermapResponse): DataWeatherCity {
        return DataWeatherCity(
            nameCity = weatherOpenWeathermapResponse.name.toString(),
            tempInCity = weatherOpenWeathermapResponse.main?.temp.toString(),
            weatherInCity = weatherOpenWeathermapResponse.weather?.get(0)?.description.toString(),
            wind = weatherOpenWeathermapResponse.wind?.speed.toString(),
            icon = weatherOpenWeathermapResponse.weather?.get(0)?.icon.toString()
        )
    }

    private fun parseDateWeatherYandexCity(weatherYandexResponse: WeatherYandexResponse): DataWeatherCity {
        return DataWeatherCity(
            nameCity = weatherYandexResponse.info.tzinfo.name,
            tempInCity = weatherYandexResponse.fact.temp.toString(),
            weatherInCity = weatherYandexResponse.fact.condition,
            wind = weatherYandexResponse.fact.windSpeed.toString(),
            icon = weatherYandexResponse.fact.icon
        )
    }
}