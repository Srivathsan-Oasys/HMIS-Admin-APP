package com.hmis_tn.admin.network

import com.hmis_tn.admin.ui.home.model.OpListResp
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val GET_OP_LIST = "getOpList"
    }

    @GET(GET_OP_LIST)
    fun getOpList(): Call<OpListResp>
}