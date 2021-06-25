package com.hmis_tn.admin.ui.patientSearch.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.hmis_tn.admin.network.ApiService
import com.hmis_tn.admin.network.NetworkClient
import com.hmis_tn.admin.ui.patientSearch.model.GenderListResponseModel
import com.hmis_tn.admin.ui.patientSearch.model.GenderReq
import com.hmis_tn.admin.ui.patientSearch.model.PatientListResponseModel
import com.hmis_tn.admin.ui.patientSearch.model.PatitentListRequest
import com.hmis_tn.admin.utils.ProgressUtil
import retrofit2.Callback

class PatientListViewModel : ViewModel() {

    private lateinit var apiService: ApiService

    fun getGender(
        context: Context,
        loginReq: GenderReq,
        token: String?,
        userId: Int?,
        callback: Callback<GenderListResponseModel>
    ) {
        ProgressUtil.startProgressDialog(context)
        apiService = NetworkClient.getNetworkClient()
        val call = apiService.postGenderData(token, userId, loginReq)
        call.enqueue(callback)
    }

    fun getPatitentList(
        context: Context,
        loginReq: PatitentListRequest,
        token: String?,
        userId: Int?,
        callback: Callback<PatientListResponseModel>
    ) {
        ProgressUtil.startProgressDialog(context)
        apiService = NetworkClient.getNetworkClient()
        val call = apiService.getPatitentList(token, userId, loginReq)
        call.enqueue(callback)

    }
}