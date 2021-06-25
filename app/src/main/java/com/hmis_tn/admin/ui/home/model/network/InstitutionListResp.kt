package com.hmis_tn.admin.ui.home.model.network

data class InstitutionListResp(
    val code: Int? = 0,
    val message: String? = "",
    val responseContents: List<ResponseContent>? = listOf()
)