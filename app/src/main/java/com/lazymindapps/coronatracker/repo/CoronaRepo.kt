package com.lazymindapps.coronatracker.repo

import com.lazymindapps.coronatracker.model.Countryresult
import com.lazymindapps.coronatracker.retrofit.RetrofitInstance
import retrofit2.Response

class CoronaRepo {

    suspend fun getAllCountryResult(country:String):Response<Countryresult>{
        return RetrofitInstance.services.getCountryResult(country)
    }

    suspend fun getWorldResult():Response<Countryresult>{
        return RetrofitInstance.services.getWorldResult()
    }
}