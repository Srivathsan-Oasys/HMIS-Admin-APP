package com.hmis_tn.admin.patientSearch.model

data class GenderListResponseModel(
    val responseContents: List<GenderResponse>,
    val statusCode: Int
)