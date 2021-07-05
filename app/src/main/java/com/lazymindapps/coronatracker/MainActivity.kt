package com.lazymindapps.coronatracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.lazymindapps.coronatracker.repo.CoronaRepo
import com.lazymindapps.coronatracker.viewmodel.CoronaViewModel
import com.lazymindapps.coronatracker.viewmodel.CoronaViewModelProvider
import kotlinx.coroutines.CoroutineScope

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: CoronaViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repo = CoronaRepo()
        val viewModelFactory = CoronaViewModelProvider(repo)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CoronaViewModel::class.java)
    }
}