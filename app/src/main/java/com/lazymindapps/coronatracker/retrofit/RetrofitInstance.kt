package com.lazymindapps.coronatracker.retrofit

import com.lazymindapps.coronatracker.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {


        private val retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        val services: RetrofitService by lazy {
            retrofit.create(RetrofitService::class.java)
        }
    }
}
