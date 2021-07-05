package com.lazymindapps.coronatracker.model

import com.google.gson.annotations.SerializedName

data class Countryresult(
        @SerializedName("confirmed") val confirmed: ResultObj,
        @SerializedName("recovered") val recovered: ResultObj,
        @SerializedName("deaths")
        val deaths: ResultObj
)
