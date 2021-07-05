package com.lazymindapps.coronatracker.model

import com.google.gson.annotations.SerializedName

data class ResultObj (
        @SerializedName("value") val value:Int,
        @SerializedName("detail") val detail:String
        )