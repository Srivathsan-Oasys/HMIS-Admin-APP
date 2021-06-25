package com.hmis_tn.admin.ui.patientSearch.model

data class GenderReq(
    val is_active: Boolean? = true,
    val codename: String? = "001",
    val sortOrder: String? = "DESC",
    val sortField: String? = "name",
    val search: String? = "Superhmis",
    val pageNo: Int? = 0,
    val paginationSize: Int? = 10
)

//{"pageNo":0,"paginationSize":10,"sortField":"name","sortOrder":"DESC","codename":"001","is_active":1,"search":"Superhmis"}