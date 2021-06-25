package com.hmis_tn.admin.ui.patientSearch.model

data class PatientListResponseModel(
    var responseContents: List<PatientData>,
    var code: Int,
    var message: String
)