package com.lazymindapps.coronatracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lazymindapps.coronatracker.repo.CoronaRepo

class CoronaViewModelProvider(val repo:CoronaRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoronaViewModel(repo) as T

    }

}