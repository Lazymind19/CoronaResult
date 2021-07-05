package com.lazymindapps.coronatracker.retrofit

import com.lazymindapps.coronatracker.Constant.Companion.APIKEY
import com.lazymindapps.coronatracker.Constant.Companion.SECOND
import com.lazymindapps.coronatracker.model.Countryresult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("api/countries/{country}")
    suspend fun getCountryResult(@Path("country") country: String): Response<Countryresult>

    @GET(SECOND+ APIKEY)
    suspend fun getWorldResult():Response<Countryresult>
}