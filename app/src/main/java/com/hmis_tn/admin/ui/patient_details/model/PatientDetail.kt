package com.hmis_tn.admin.ui.patient_details.model

data class PatientDetail(
    val aadhaar_number: String? = "",
    val address_line1: String? = "",
    val address_line2: String? = "",
    val address_line3: String? = "",
    val address_line4: String? = "",
    val address_line5: String? = "",
    val airport_name: Any? = Any(),
    val alternate_number: String? = "",
    val attender_contact_number: Any? = Any(),
    val attender_name: Any? = Any(),
    val bill_class: Any? = Any(),
    val block_uuid: Int? = 0,
    val city_uuid: Int? = 0,
    val community_detail: Any? = Any(),
    val community_uuid: Int? = 0,
    val complication_detail: Any? = Any(),
    val complication_uuid: Int? = 0,
    val contact_history: Boolean? = false,
    val contact_history_details: Any? = Any(),
    val corporation_uuid: Any? = Any(),
    val correction_reason: Any? = Any(),
    val country_uuid: Int? = 0,
    val created_by: Int? = 0,
    val created_date: String? = "",
    val date_of_onset: Any? = Any(),
    val death_approved_by: Int? = 0,
    val death_coments: Any? = Any(),
    val death_confirmed_by: Int? = 0,
    val death_place: String? = "",
    val death_updated_by: Int? = 0,
    val death_updated_date: Any? = Any(),
    val district_uuid: Int? = 0,
    val email: String? = "",
    val father_name: Any? = Any(),
    val height: String? = "",
    val ili: Boolean? = false,
    val income_uuid: Int? = 0,
    val is_active: Boolean? = false,
    val is_death_confirmed: Any? = Any(),
    val is_email_communication_preference: Any? = Any(),
    val is_rapid_test: Boolean? = false,
    val is_sms_communication_preference: Any? = Any(),
    val lab_to_facility_uuid: Int? = 0,
    val location_travelled: Any? = Any(),
    val marital_uuid: Int? = 0,
    val mobile: String? = "",
    val modified_by: Int? = 0,
    val modified_date: String? = "",
    val municipality_uuid: Any? = Any(),
    val nationality_type_detail: Any? = Any(),
    val nationality_type_uuid: Int? = 0,
    val no_symptom: Boolean? = false,
    val occupation_uuid: Int? = 0,
    val op_status: Any? = Any(),
    val other_proof_number: Any? = Any(),
    val other_proof_uuid: Int? = 0,
    val out_come_date: Any? = Any(),
    val out_come_type_detail: Any? = Any(),
    val out_come_type_uuid: Int? = 0,
    val para_1: Any? = Any(),
    val patient_uuid: Int? = 0,
    val photo_path: String? = "",
    val pin_status: Any? = Any(),
    val pincode: String? = "",
    val place_uuid: Int? = 0,
    val quarantine_status_date: Any? = Any(),
    val quarentine_status: Any? = Any(),
    val quarentine_status_type_detail: Any? = Any(),
    val quarentine_status_type_uuid: Int? = 0,
    val referred_doctor: Any? = Any(),
    val relation_type_uuid: Int? = 0,
    val religion_detail: Any? = Any(),
    val religion_uuid: Int? = 0,
    val remarks: Any? = Any(),
    val repeat_test: Boolean? = false,
    val repeat_test_date: Any? = Any(),
    val repeat_test_type_uuid: Int? = 0,
    val revision: Boolean? = false,
    val sample_type_uuid: Int? = 0,
    val sari: Boolean? = false,
    val smart_ration_number: String? = "",
    val state_uuid: Int? = 0,
    val symptom_duration: Any? = Any(),
    val symptoms: Any? = Any(),
    val taluk_uuid: Int? = 0,
    val test_location: Any? = Any(),
    val test_result: Boolean? = false,
    val travel_history: Any? = Any(),
    val travel_history_date: Any? = Any(),
    val travel_history_to_date: Any? = Any(),
    val treatment_category_uuid: Int? = 0,
    val treatment_plan_detail: Any? = Any(),
    val treatment_plan_uuid: Int? = 0,
    val underline_medicine_condition_uuid: Int? = 0,
    val underline_medicine_details: Any? = Any(),
    val uuid: Int? = 0,
    val village_uuid: Int? = 0,
    val weight: String? = ""
)