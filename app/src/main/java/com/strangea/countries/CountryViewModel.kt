package com.strangea.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.strangea.countries.api.ApiManager
import com.strangea.countries.entities.Country
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response

class CountryViewModel : ViewModel() {
    val countryList = MutableLiveData<Response<List<Country>>>()

    fun loadCountries() {
        ApiManager.getApiManager().service.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : SingleObserver<List<Country>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(countries: List<Country>) {
                    countryList.postValue(Response.success(countries))
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        e.response().errorBody()?.let {
                            countryList.postValue(Response.error(e.code(), it))
                        }
                    }
                }
            })
    }
}
