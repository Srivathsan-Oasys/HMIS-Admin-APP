package com.hmis_tn.admin.ui.login.model

data class LoginResp(
    val responseContents: ResponseContents? = ResponseContents(),
    val statusCode: Int? = 0
)