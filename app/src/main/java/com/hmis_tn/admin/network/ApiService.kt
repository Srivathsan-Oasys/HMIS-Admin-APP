package com.hmis_tn.admin.network

import com.hmis_tn.admin.patientSearch.model.GenderListResponseModel
import com.hmis_tn.admin.patientSearch.model.GenderReq
import com.hmis_tn.admin.ui.home.model.response.OpListResp
import com.hmis_tn.admin.ui.login.model.LoginReq
import com.hmis_tn.admin.ui.login.model.LoginResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    companion object {
        const val POST_LOGIN = "DEVHMIS-Login/1.0.0/api/authentication/loginNew"
        const val GET_OP_LIST = "DEVHMIS-EMR/v1/api/encounter/getEncounterDashboardPatientCount"
        const val POST_GENDER_LIST = "DEVAppmaster/v1/api/gender/getGender"
    }

    @POST(POST_LOGIN)
    fun postLogin(
        @Body loginReq: LoginReq
    ): Call<LoginResp>

    @POST(GET_OP_LIST)
    fun getOpList(
        @Header("Authorization") authorization: String?,
        @Header("user_uuid") user_uuid: Int,
        @Body opListReq: OpListReq
    ): Call<OpListResp>


    @POST(POST_GENDER_LIST)
    fun postGenderData(
        @Header("Authorization") authorization: String?,
        @Header("user_uuid") user_uuid: Int?,
        @Body loginReq: GenderReq
    ): Call<GenderListResponseModel>
}