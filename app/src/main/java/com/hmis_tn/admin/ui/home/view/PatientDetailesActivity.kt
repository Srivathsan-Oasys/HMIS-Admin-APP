package com.hmis_tn.admin.ui.home.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.home.model.network.OpListResp
import com.hmis_tn.admin.ui.home.model.patientDetails.PatientDetail
import com.hmis_tn.admin.ui.home.model.patientDetails.PatientDetails
import com.hmis_tn.admin.ui.home.view_model.HomeViewModel
import com.hmis_tn.admin.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientDetailesActivity: AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private var patient_req:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detailes)

        getBundleData()
        initializeViews()

        getPatientDetails()

    }

    private fun getBundleData() {

    }

    private fun initializeViews() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]


    }

    private fun getPatientDetails(){

        val pref = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE)
        val authorization = "Bearer ${pref.getString(Constants.PREF_ACCESS_TOKEN, "")}"
        val userUuid = pref.getInt(Constants.PREF_USER_UUID, 0)

/*
        homeViewModel.getPatientDetailes(this,authorization,userUuid, patient_req!!,
            object : Callback<PatientDetails> {
                override fun onResponse(call: Call<PatientDetails>, response: Response<PatientDetails>) {
                    println("success"+response.body())
                }

                override fun onFailure(call: Call<PatientDetails>, t: Throwable) {
                    println("success"+t.localizedMessage)

                }
            }

        )
*/
    }
}