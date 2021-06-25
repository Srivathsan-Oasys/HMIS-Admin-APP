package com.hmis_tn.admin.ui.patient_details.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.patient_details.model.PatientDetailsReq
import com.hmis_tn.admin.ui.patient_details.model.PatientDetailsResp
import com.hmis_tn.admin.ui.patient_details.view_model.PatientDetailsViewModel
import com.hmis_tn.admin.utils.Constants
import com.hmis_tn.admin.utils.ProgressUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientDetailsActivity : AppCompatActivity() {

    private lateinit var patientDetailsViewModel: PatientDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detailes)

        initializeViews()

        val patientId = intent.getStringExtra(Constants.INTENT_PATIENT_ID) ?: "0"
        getPatientDetails(patientId)
    }

    private fun initializeViews() {
        patientDetailsViewModel = ViewModelProvider(this)[PatientDetailsViewModel::class.java]
    }

    private fun getPatientDetails(patientId: String) {
        val pref = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE)
        val authorization = "Bearer ${pref.getString(Constants.PREF_ACCESS_TOKEN, "")}"
        val userUuid = pref.getInt(Constants.PREF_USER_UUID, 0)

        val body = PatientDetailsReq(
            patientId
        )

        patientDetailsViewModel.getPatientDetails(
            this,
            authorization,
            userUuid,
            body,
            patientDetailsCallback
        )
    }

    private val patientDetailsCallback = object : Callback<PatientDetailsResp> {
        override fun onResponse(
            call: Call<PatientDetailsResp>,
            response: Response<PatientDetailsResp>
        ) {
            ProgressUtil.dismissProgressDialog()

        }

        override fun onFailure(call: Call<PatientDetailsResp>, t: Throwable) {
            ProgressUtil.dismissProgressDialog()
            t.printStackTrace()
        }
    }
}