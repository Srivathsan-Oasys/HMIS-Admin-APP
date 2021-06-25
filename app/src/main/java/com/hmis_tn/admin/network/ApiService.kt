package com.hmis_tn.admin.network

import com.hmis_tn.admin.patientSearch.model.GenderListResponseModel
import com.hmis_tn.admin.patientSearch.model.GenderReq
import com.hmis_tn.admin.patientSearch.model.PatientListResponseModel
import com.hmis_tn.admin.patientSearch.model.PatitentListRequest
import com.hmis_tn.admin.ui.home.model.network.InstitutionListReq
import com.hmis_tn.admin.ui.home.model.network.InstitutionListResp
import com.hmis_tn.admin.ui.home.model.patientDetails.PatientDetails
import com.hmis_tn.admin.ui.login.model.LoginReq
import com.hmis_tn.admin.ui.login.model.LoginResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val POST_LOGIN = "DEVHMIS-Login/1.0.0/api/authentication/loginNew"
        const val GET_OP_LIST = "DEVHMIS-EMR/v1/api/encounter/getEncounterDashboardPatientCount"
        const val PATIENT_DETAILS = "UATregistration/v1/api/patient/getById"
        const val POST_GENDER_LIST = "DEVAppmaster/v1/api/gender/getGender"
        const val POST_PATIENT_LIST = "DEVHMIS-EMR/v1/api/encounter/getEncounterDashboardPatientInfo"
    }

    @POST(POST_LOGIN)
    fun postLogin(
        @Body loginReq: LoginReq
    ): Call<LoginResp>

    @POST(GET_OP_LIST)
    fun getOpList(
        @Header("Authorization") authorization: String?,
        @Header("user_uuid") user_uuid: Int,
        @Body institutionListReq: InstitutionListReq
    ): Call<InstitutionListResp>

    @POST(PATIENT_DETAILS)
    fun getPatientDetails(
        @Header("Authorization") authorization: String?,
        @Header("user_uuid") user_uuid: Int,
        @Query("patientId") patientID:Int
    ): Call<PatientDetails>


    @POST(POST_GENDER_LIST)
    fun postGenderData(
        @Header("Authorization") authorization: String?,
        @Header("user_uuid") user_uuid: Int?,
        @Body loginReq: GenderReq
    ): Call<GenderListResponseModel>

    @POST(POST_PATIENT_LIST)
    fun getPatitentList(
        @Header("Authorization") authorization: String?,
        @Header("user_uuid") user_uuid: Int?,
        @Body loginReq: PatitentListRequest
    ): Call<PatientListResponseModel>
}