package com.strangea.countries.api

import com.strangea.countries.entities.Country
import io.reactivex.Single
import retrofit2.http.GET

interface RestCountriesService {

    @GET("all")
    fun getCountries(): Single<List<Country>>
}
