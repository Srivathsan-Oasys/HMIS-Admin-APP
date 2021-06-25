package com.hmis_tn.admin.network

import com.hmis_tn.admin.ui.patientSearch.model.GenderListResponseModel
import com.hmis_tn.admin.ui.patientSearch.model.GenderReq
import com.hmis_tn.admin.ui.patientSearch.model.PatientListResponseModel
import com.hmis_tn.admin.ui.patientSearch.model.PatitentListRequest
import com.hmis_tn.admin.ui.home.model.network.InstitutionListReq
import com.hmis_tn.admin.ui.home.model.network.InstitutionListResp
import com.hmis_tn.admin.ui.login.model.LoginReq
import com.hmis_tn.admin.ui.login.model.LoginResp
import com.hmis_tn.admin.ui.patient_details.model.PatientDetailsReq
import com.hmis_tn.admin.ui.patient_details.model.PatientDetailsResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    companion object {
        const val POST_LOGIN = "PRODHMIS-Login/1.0.0/api/authentication/loginNew"
        const val GET_OP_LIST = "PRODHMIS-EMR/v1/api/encounter/getEncounterDashboardPatientCount"
        const val POST_GENDER_LIST = "PRODAppmaster/v1/api/gender/getGender"
        const val POST_PATIENT_LIST =
            "PRODHMIS-EMR/v1/api/encounter/getEncounterDashboardPatientInfo"
        const val POST_PATIENT_DETAILS = "PRODregistration/v1/api/patient/getById"
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

    @POST(POST_GENDER_LIST)
    fun postGenderData(
        @Header("Authorization") authorization: String?,
        @Header("user_uuid") user_uuid: Int?,
        @Body genderReq: GenderReq
    ): Call<GenderListResponseModel>

    @POST(POST_PATIENT_LIST)
    fun getPatitentList(
        @Header("Authorization") authorization: String?,
        @Header("user_uuid") user_uuid: Int?,
        @Body patitentListRequest: PatitentListRequest
    ): Call<PatientListResponseModel>

    @POST(POST_PATIENT_DETAILS)
    fun getPatientDetails(
        @Header("Authorization") authorization: String?,
        @Header("user_uuid") user_uuid: Int?,
        @Body patientDetailsReq: PatientDetailsReq
    ): Call<PatientDetailsResp>
}