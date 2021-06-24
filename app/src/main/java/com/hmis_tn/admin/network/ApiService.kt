package com.hmis_tn.admin.network

import com.hmis_tn.admin.ui.home.model.response.OpListResp
import com.hmis_tn.admin.ui.login.model.LoginReq
import com.hmis_tn.admin.ui.login.model.LoginResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object {
        const val POST_LOGIN = "DEVHMIS-Login/1.0.0/api/authentication/loginNew"
        const val GET_OP_LIST = "DEVHMIS-EMR/v1/api/encounter/getEncounterDashboardPatientCount"
    }

    @POST(POST_LOGIN)
    fun postLogin(
        @Body loginReq: LoginReq
    ): Call<LoginResp>

    @GET(GET_OP_LIST)
    fun getOpList(): Call<OpListResp>
}