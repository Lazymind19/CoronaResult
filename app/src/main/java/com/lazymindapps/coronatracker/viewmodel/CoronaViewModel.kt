package com.lazymindapps.coronatracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazymindapps.coronatracker.model.Countryresult
import com.lazymindapps.coronatracker.repo.CoronaRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class CoronaViewModel(private val repo: CoronaRepo) : ViewModel() {
    var worldResult: MutableLiveData<Response<Countryresult>> = MutableLiveData()
    var countryresult: MutableLiveData<Response<Countryresult>> = MutableLiveData()

   suspend fun getWorldResult() =


            viewModelScope.launch {
                val response: Response<Countryresult> = repo.getWorldResult()
                worldResult.value = response

            }

  suspend  fun getcountryResult(countryName: String) =
            viewModelScope.launch {
                val response: Response<Countryresult> = repo.getAllCountryResult(countryName)
                countryresult.value = response

            }
}