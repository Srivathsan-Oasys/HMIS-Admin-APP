package com.hmis_tn.admin.ui.home.model

data class Institution(
    var encounter_type_name_1: String? = "",
    var facility_category_name_1: String? = "",
    var facility_category_uuid_1: Int? = 0,
    var gender_name_1: String? = "",
    var gender_uuid_1: Int? = 0,
    var patient_count_1: Int? = 0,

    var encounter_type_name_2: String? = "",
    var facility_category_name_2: String? = "",
    var facility_category_uuid_2: Int? = 0,
    var gender_name_2: String? = "",
    var gender_uuid_2: Int? = 0,
    var patient_count_2: Int? = 0,

    var encounter_type_name_3: String? = "",
    var facility_category_name_3: String? = "",
    var facility_category_uuid_3: Int? = 0,
    var gender_name_3: String? = "",
    var gender_uuid_3: Int? = 0,
    var patient_count_3: Int? = 0

)