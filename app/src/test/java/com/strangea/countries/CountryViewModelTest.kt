package com.strangea.countries

import com.strangea.countries.api.ApiManager
import com.strangea.countries.api.RestCountriesService
import com.strangea.countries.entities.Country
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

class CountryViewModelTest: BaseTest(){

    private val classUnderTest = CountryViewModel()

    @Test
    fun testCountryViewModel(){
        classUnderTest.loadCountries()

        assertEquals(classUnderTest.countryList.value?.isSuccessful,true)
        assertEquals(classUnderTest.countryList.value?.body()?.size,1)
        assertEquals(classUnderTest.countryList.value?.body()?.get(0)?.flag,"flag")

    }
}