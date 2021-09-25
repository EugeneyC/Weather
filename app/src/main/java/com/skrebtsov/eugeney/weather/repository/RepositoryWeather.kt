package com.skrebtsov.eugeney.weather.repository

import com.skrebtsov.eugeney.weather.getCoordCity
import com.skrebtsov.eugeney.weather.model.UseOneDataSource
import com.skrebtsov.eugeney.weather.model.UseTwoDataSource
import com.skrebtsov.eugeney.weather.model.WeatherApiOne
import com.skrebtsov.eugeney.weather.model.WeatherApiTwo
import com.skrebtsov.eugeney.weather.model.models.firstapi.DataWeatherCity
import com.skrebtsov.eugeney.weather.model.models.firstapi.WeatherOpenWeathermapResponse
import com.skrebtsov.eugeney.weather.model.models.secondapi.WeatherYandexResponse
import com.skrebtsov.eugeney.weather.viewmodel.IViewModel
import com.skrebtsov.eugeney.weather.viewmodel.WeatherByCityViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryWeather @Inject constructor(
    private val useOneDataSource: UseOneDataSource,
    private val useTwoDataSource: UseTwoDataSource,
) {

    fun startAutoUpdate(viewModel: IViewModel, city: String) {
        val result = Observable.merge(
            getWeatherByCityAutoUpdate(city),
            getWeatherYandexByCityAutoUpdate(city)
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
                viewModel.showWeather(it)
            },
                {
                    viewModel.showError()
                })
    }

    private fun getWeatherYandexByCityAutoUpdate(city: String): Observable<DataWeatherCity> {
        val coord = getCoordCity(city)
        return useTwoDataSource.getWeatherApiYandex()
            .weatherYandex(lon = coord?.lon.toString(), lat = coord?.lat.toString())
            .delay(5, TimeUnit.MINUTES)
            .map { parseDateWeatherYandexCity(it) }
    }

    private fun getWeatherByCityAutoUpdate(city: String): Observable<DataWeatherCity> {
        return useOneDataSource.getWeatherApiOpenweathermap().getWeatherInCity(cityName = city)
            .delay(3, TimeUnit.MINUTES)
            .map { parseDateWeatherCity(it) }
    }

    fun getWeatherByCity(viewModel: IViewModel, city: String) {
        val result =
            useOneDataSource.getWeatherApiOpenweathermap().getWeatherInCity(cityName = city)
                .map { parseDateWeatherCity(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewModel.showWeather(it)
                }, {
                    viewModel.showError()
                })
    }

    private fun parseDateWeatherYandexCity(weatherYandexResponse: WeatherYandexResponse): DataWeatherCity {
        return DataWeatherCity(
            tempInCity = weatherYandexResponse.fact.temp.toString(),
            weatherInCity = weatherYandexResponse.fact.condition,
            wind = weatherYandexResponse.fact.windSpeed.toString(),
            icon = weatherYandexResponse.fact.icon
        )
    }

    private fun parseDateWeatherCity(weatherOpenWeatherMapResponse: WeatherOpenWeathermapResponse): DataWeatherCity {
        return DataWeatherCity(
            nameCity = weatherOpenWeatherMapResponse.name.toString(),
            tempInCity = weatherOpenWeatherMapResponse.main?.temp.toString(),
            weatherInCity = weatherOpenWeatherMapResponse.weather?.get(0)?.description.toString(),
            wind = weatherOpenWeatherMapResponse.wind?.speed.toString(),
            icon = weatherOpenWeatherMapResponse.weather?.get(0)?.icon.toString()
        )
    }
}