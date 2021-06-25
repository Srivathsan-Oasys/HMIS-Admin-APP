package com.hmis_tn.admin.ui.patientSearch.model

data class GenderResponse(
    val code: String,
    val display_order: Int,
    val is_active: Boolean,
    var name: String,
    val revision: Int,
    var uuid: Int
)