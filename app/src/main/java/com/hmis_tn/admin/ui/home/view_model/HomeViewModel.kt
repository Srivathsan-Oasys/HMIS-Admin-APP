package com.hmis_tn.admin.ui.home.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.hmis_tn.admin.network.ApiService
import com.hmis_tn.admin.network.NetworkClient
import com.hmis_tn.admin.ui.home.model.network.InstitutionListReq
import com.hmis_tn.admin.ui.home.model.network.InstitutionListResp
import com.hmis_tn.admin.ui.home.model.patientDetails.PatientDetails
import com.hmis_tn.admin.utils.ProgressUtil
import retrofit2.Callback

class HomeViewModel : ViewModel() {

    private lateinit var apiService: ApiService

    fun getOpList(
        context: Context,
        authorization: String,
        user_uuid: Int,
        institutionListReq: InstitutionListReq,
        callback: Callback<InstitutionListResp>
    ) {
        ProgressUtil.startProgressDialog(context)
        apiService = NetworkClient.getNetworkClient()
        val call = apiService.getOpList(authorization, user_uuid, institutionListReq)
        call.enqueue(callback)
    }
    fun getPatientDetailes(
        context: Context,
        authorization: String,
        user_uuid: Int,
        patientID: Int,
        callback: Callback<PatientDetails>
    ) {
        ProgressUtil.startProgressDialog(context)
        apiService = NetworkClient.getNetworkClient()
        val call = apiService.getPatientDetails(authorization, user_uuid,patientID)
        call.enqueue(callback)
    }
}