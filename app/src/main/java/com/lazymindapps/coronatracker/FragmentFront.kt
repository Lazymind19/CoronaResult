package com.lazymindapps.coronatracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.lazymindapps.coronatracker.databinding.FragmentFrontBinding
import com.lazymindapps.coronatracker.model.Countryresult
import com.lazymindapps.coronatracker.model.ResultObj
import com.lazymindapps.coronatracker.repo.CoronaRepo
import com.lazymindapps.coronatracker.viewmodel.CoronaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.StringReader
import java.text.DateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class FragmentFront : Fragment(),CoroutineScope {
    lateinit var bindingFront:FragmentFrontBinding
    lateinit var viewModel:CoronaViewModel
    lateinit var repo:CoronaRepo
    lateinit var coronaDetail :String
    private var countryName = ""
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        bindingFront = FragmentFrontBinding.inflate(inflater,container,false)
        repo = CoronaRepo()
        viewModel = (activity as MainActivity).viewModel

        return bindingFront.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var date: Calendar = Calendar.getInstance()
        var currentDate: String = DateFormat.getDateInstance(DateFormat.FULL).format(date.time)
        bindingFront.tvDate.text = currentDate

        bindingFront.cardbtn.setOnClickListener {
         val cName:String=   bindingFront.etCountry.text.toString()
            if (cName!=""){
                countryName = cName
                launch {
                    viewModel.getcountryResult(countryName)

                    viewModel.countryresult.observe(viewLifecycleOwner, { response ->
                        if (response.isSuccessful) {
                            coronaDetail = response.body()!!.toString()
                            Log.d("Individualcountry", "onViewCreated: $coronaDetail")
                            val confirmedValue = response.body()!!.confirmed.value
                            val deathsValue = response.body()!!.deaths.value
                            val recoveredValue = response.body()!!.recovered.value
                            bindingFront.apply {
                                tvCountryName.text = countryName.capitalize()
                                tvTotalValue1.text = confirmedValue.toString()
                                tvDeathValue.text = deathsValue.toString()
                                tvRecoveredValue.text = recoveredValue.toString()
                            }
                        } else {
                            Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                        }
                    })

                }


            }

        }

        launch {
            if (countryName=="") {
                viewModel.getWorldResult()
                viewModel.worldResult.observe(viewLifecycleOwner, { response ->
                    if (response.isSuccessful) {
                        val confirmedValue = response.body()!!.confirmed.value
                        val deathsValue = response.body()!!.deaths.value
                        val recoveredValue = response.body()!!.recovered.value
                        bindingFront.apply {
                            tvCountryName.text = "World-Wide"
                            tvTotalValue1.text = confirmedValue.toString()
                            tvDeathValue.text = deathsValue.toString()
                            tvRecoveredValue.text = recoveredValue.toString()
                        }

                    } else {
                        Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                    }
                })
            }

//
//            activity?.let {
//                viewModel.worldResult.observe(it,
//                        { response ->
//                            if (response.isSuccessful) {
//                                coronaDetail = response.body()!!.toString()
//                            } else {
//                                Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_LONG).show()
//                            }
//
//                        })
//            }





            }
        }

    }









