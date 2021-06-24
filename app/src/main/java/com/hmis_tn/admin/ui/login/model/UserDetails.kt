package com.hmis_tn.admin.ui.login.model

data class UserDetails(
    val SessionId: String? = "",
    val access_token: String? = "",
    val role: Role? = Role(),
    val user_type: UserType? = UserType(),
    val uuid: Int? = 0
)