package com.strangea.countries.api

import com.google.gson.GsonBuilder
import com.strangea.countries.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager private constructor() {

    var service: RestCountriesService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        service = retrofit.create(RestCountriesService::class.java)
    }

    companion object {

        private var apiManager: ApiManager? = null

        fun setInstance(apiManager: ApiManager) {
            Companion.apiManager = apiManager
        }

        fun getApiManager(): ApiManager {
            if (apiManager == null) {
                apiManager = ApiManager()
            }
            return apiManager!!
        }
    }
}
