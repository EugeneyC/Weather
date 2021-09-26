package com.skrebtsov.eugeney.weather.domain

import com.skrebtsov.eugeney.weather.repository.RepositoryTwoApi
import javax.inject.Inject

class UseTwoDataSource @Inject constructor(private val repositoryTwoApi: RepositoryTwoApi) {

}