package com.hmis_tn.admin.patientSearch.model

data class PatitentListRequest(
    var encounter_type_id: String?="",
    var facility_category_id: String?="",
    var from_datetime: String?="",
    var gender_id: String?="",
    var to_datetime: String?=""
)