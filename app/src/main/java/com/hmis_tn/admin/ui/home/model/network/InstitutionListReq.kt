package com.hmis_tn.admin.ui.home.model.network

data class InstitutionListReq(
    val encounter_type_id: String? = "",
    val from_datetime: String? = "",
    val to_datetime: String? = ""
)