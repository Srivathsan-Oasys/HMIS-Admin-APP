package com.hmis_tn.admin.ui.home.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.hmis_tn.admin.network.ApiService
import com.hmis_tn.admin.network.NetworkClient
import com.hmis_tn.admin.ui.home.model.OpListResp
import com.hmis_tn.admin.utils.ProgressUtil
import retrofit2.Callback

class HomeViewModel : ViewModel() {

    private lateinit var apiService: ApiService

    fun getOpList(context: Context, callback: Callback<OpListResp>) {
        ProgressUtil.startProgressDialog(context)
        apiService = NetworkClient.getNetworkClient()
        val call = apiService.getOpList()
        call.enqueue(callback)

    }
}