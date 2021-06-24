package com.hmis_tn.admin.patientSearch.view_model

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.hmis_tn.admin.network.ApiService
import com.hmis_tn.admin.network.NetworkClient
import com.hmis_tn.admin.patientSearch.model.GenderListResponseModel
import com.hmis_tn.admin.patientSearch.model.GenderReq
import com.hmis_tn.admin.ui.login.model.LoginReq
import com.hmis_tn.admin.ui.login.model.LoginResp
import com.hmis_tn.admin.utils.Constants
import com.hmis_tn.admin.utils.ProgressUtil
import retrofit2.Callback

class PatientListViewModel : ViewModel() {

    private lateinit var apiService: ApiService

    fun getGender(
        context: Context,
        loginReq: GenderReq,
        token:String?,
        userId:Int?,
        callback: Callback<GenderListResponseModel>
    ) {
        ProgressUtil.startProgressDialog(context)
        apiService = NetworkClient.getNetworkClient()
        val call = apiService.postGenderData(token,userId,loginReq)
        call.enqueue(callback)
    }
}