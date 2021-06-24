package com.hmis_tn.admin.ui.login.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.hmis_tn.admin.network.ApiService
import com.hmis_tn.admin.network.NetworkClient
import com.hmis_tn.admin.ui.login.model.LoginReq
import com.hmis_tn.admin.ui.login.model.LoginResp
import com.hmis_tn.admin.utils.ProgressUtil
import retrofit2.Callback

class LoginViewModel : ViewModel() {

    private lateinit var apiService: ApiService

    fun postLogin(
        context: Context,
        loginReq: LoginReq,
        callback: Callback<LoginResp>
    ) {
        ProgressUtil.startProgressDialog(context)
        apiService = NetworkClient.getNetworkClient()
        val call = apiService.postLogin(loginReq)
        call.enqueue(callback)
    }
}