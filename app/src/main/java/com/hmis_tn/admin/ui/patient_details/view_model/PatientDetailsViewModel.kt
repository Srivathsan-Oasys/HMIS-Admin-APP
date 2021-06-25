package com.hmis_tn.admin.ui.patient_details.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.hmis_tn.admin.network.ApiService
import com.hmis_tn.admin.network.NetworkClient
import com.hmis_tn.admin.ui.patient_details.model.PatientDetailsReq
import com.hmis_tn.admin.ui.patient_details.model.PatientDetailsResp
import com.hmis_tn.admin.utils.ProgressUtil
import retrofit2.Callback

class PatientDetailsViewModel : ViewModel() {

    private lateinit var apiService: ApiService

    fun getPatientDetails(
        context: Context,
        authorization: String,
        user_uuid: Int,
        patientDetailsReq: PatientDetailsReq,
        callback: Callback<PatientDetailsResp>
    ) {
        ProgressUtil.startProgressDialog(context)
        apiService = NetworkClient.getNetworkClient()
        val call = apiService.getPatientDetails("en", authorization, user_uuid, patientDetailsReq)
        call.enqueue(callback)
    }
}