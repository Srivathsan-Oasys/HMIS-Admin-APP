package com.hmis_tn.admin.ui.patient_details.model

data class PatientDetailsResp(
    val message: String? = "",
    val responseContent: ResponseContent? = ResponseContent(),
    val statusCode: Int? = 0
)