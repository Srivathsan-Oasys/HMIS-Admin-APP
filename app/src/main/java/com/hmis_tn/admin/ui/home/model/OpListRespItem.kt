package com.hmis_tn.admin.ui.home.model

data class OpListRespItem(
    val encounter_type_name: String? = "",
    val facility_category_name: String? = "",
    val facility_category_uuid: Int? = 0,
    val gender_name: String? = "",
    val gender_uuid: Int? = 0,
    val patient_count: Int? = 0
)