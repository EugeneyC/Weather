package com.skrebtsov.eugeney.weather.domain

import com.skrebtsov.eugeney.weather.repository.RepositoryOneApi
import com.skrebtsov.eugeney.weather.repository.RepositoryTwoApi
import com.skrebtsov.eugeney.weather.view.viewmodel.IViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UseCaseWeatherAutoUpdate @Inject constructor(
    private val repositoryOneApi: RepositoryOneApi,
    private val repositoryTwoApi: RepositoryTwoApi
) {
    fun startAutoUpdate(viewModel: IViewModel, city: String) {
        val result = Observable.merge(
            repositoryOneApi.getWeatherByCityAutoUpdate(city),
            repositoryTwoApi.getWeatherYandexByCityAutoUpdate(city)
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
}