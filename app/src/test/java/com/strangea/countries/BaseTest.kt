package com.strangea.countries

import com.strangea.countries.api.ApiManager
import com.strangea.countries.api.RestCountriesService
import com.strangea.countries.entities.Country
import io.reactivex.Single
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Ignore
@Config(sdk = intArrayOf(27))
@RunWith(RobolectricTestRunner::class)
open class BaseTest{

    @get:Rule
    var ruleTest = SchedulerRule()

    @Before
    fun setup(){
        val list = ArrayList<Country>()
        val country = Country()
        country.name = "country name"
        country.flag = "flag"
        list.add(country)

        val apiManager = ApiManager.getApiManager()
        apiManager.service = Mockito.mock(RestCountriesService::class.java)
        Mockito.`when`(apiManager.service.getCountries()).thenReturn(Single.just(list))
        ApiManager.setInstance(apiManager)
    }
}
