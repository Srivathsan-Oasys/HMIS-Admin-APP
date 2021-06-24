package com.hmis_tn.admin.ui.home.model.patientDetails

data class PatientDetails(
    val message: String,
    val responseContent: ResponseContent,
    val statusCode: Int
)

data class ResponseContent(
    val age: Int,
    val application_identifier: Any,
    val application_request_type_uuid: Int,
    val application_type_uuid: Int,
    val application_uuid: Any,
    val block_details: BlockDetails,
    val blood_group_details: BloodGroupDetails,
    val blood_group_uuid: Int,
    val category_details: CategoryDetails,
    val country_details: CountryDetails,
    val covid_patient_details: Any,
    val created_by: Int,
    val created_date: String,
    val district_details: DistrictDetails,
    val dob: String,
    val facility_details: FacilityDetails,
    val first_name: String,
    val gender_details: GenderDetails,
    val gender_uuid: Int,
    val id_proof_details: IdProofDetails,
    val income_details: IncomeDetails,
    val is_active: Boolean,
    val is_adult: Boolean,
    val is_dob_auto_calculate: Boolean,
    val is_maternity: Boolean,
    val last_name: String,
    val marital_details: MaritalDetails,
    val maternity_details: Any,
    val middle_name: String,
    val modified_by: Int,
    val modified_date: String,
    val occupation_details: OccupationDetails,
    val old_pin: Any,
    val package_uuid: Int,
    val para_1: Any,
    val para_2: String,
    val para_3: String,
    val para_4: String,
    val para_5: String,
    val para_6: Any,
    val patient_condition_details: List<Any>,
    val patient_detail: PatientDetail,
    val patient_scheme_no: String,
    val patient_scheme_uuid: Int,
    val patient_specimen_details: List<Any>,
    val patient_symptoms: List<Any>,
    val patient_type_detail: PatientTypeDetail,
    val patient_type_uuid: Int,
    val patient_visits: List<PatientVisit>,
    val period_detail: PeriodDetail,
    val period_uuid: Int,
    val professional_title_uuid: Int,
    val registered_date: String,
    val registred_facility_uuid: Int,
    val relation_details: RelationDetails,
    val revision: Boolean,
    val salutation_details: SalutationDetails,
    val state_details: StateDetails,
    val status: Boolean,
    val suffix_details: SuffixDetails,
    val suffix_uuid: Int,
    val taluk_details: TalukDetails,
    val tat_end_time: String,
    val tat_start_time: String,
    val title_uuid: Int,
    val uhid: String,
    val uuid: Int,
    val village_details: VillageDetails
)

class BlockDetails(
)

class BloodGroupDetails(
)

class CategoryDetails(
)

data class CountryDetails(
    val code: String,
    val name: String,
    val uuid: Int
)

data class DistrictDetails(
    val code: String,
    val name: String,
    val uuid: Int
)

data class FacilityDetails(
    val code: String,
    val name: String,
    val uuid: Int
)

data class GenderDetails(
    val code: String,
    val name: String,
    val uuid: Int
)

class IdProofDetails(
)

class IncomeDetails(
)

class MaritalDetails(
)

class OccupationDetails(
)

data class PatientDetail(
    val aadhaar_number: String,
    val address_line1: String,
    val address_line2: String,
    val address_line3: String,
    val address_line4: String,
    val address_line5: String,
    val airport_name: Any,
    val alternate_number: String,
    val attender_contact_number: String,
    val attender_name: String,
    val bill_class: Boolean,
    val block_uuid: Int,
    val city_uuid: Int,
    val community_detail: Any,
    val community_uuid: Int,
    val complication_detail: Any,
    val complication_uuid: Int,
    val contact_history: Boolean,
    val contact_history_details: Any,
    val corporation_uuid: Int,
    val correction_reason: Any,
    val country_uuid: Int,
    val created_by: Int,
    val created_date: String,
    val date_of_onset: Any,
    val death_approved_by: Int,
    val death_coments: Any,
    val death_confirmed_by: Int,
    val death_place: String,
    val death_updated_by: Int,
    val death_updated_date: Any,
    val district_uuid: Int,
    val email: String,
    val father_name: String,
    val height: String,
    val ili: Boolean,
    val income_uuid: Int,
    val is_active: Boolean,
    val is_death_confirmed: Any,
    val is_email_communication_preference: Any,
    val is_rapid_test: Boolean,
    val is_sms_communication_preference: Any,
    val lab_to_facility_uuid: Int,
    val location_travelled: Any,
    val marital_uuid: Int,
    val mobile: String,
    val modified_by: Int,
    val modified_date: String,
    val municipality_uuid: Int,
    val nationality_type_detail: Any,
    val nationality_type_uuid: Int,
    val no_symptom: Boolean,
    val occupation_uuid: Int,
    val op_status: Boolean,
    val other_proof_number: String,
    val other_proof_uuid: Int,
    val out_come_date: Any,
    val out_come_type_detail: Any,
    val out_come_type_uuid: Int,
    val para_1: Any,
    val patient_uuid: Int,
    val photo_path: String,
    val pin_status: Boolean,
    val pincode: String,
    val place_uuid: Int,
    val quarantine_status_date: Any,
    val quarentine_status: Any,
    val quarentine_status_type_detail: Any,
    val quarentine_status_type_uuid: Int,
    val referred_doctor: Any,
    val relation_type_uuid: Int,
    val religion_detail: Any,
    val religion_uuid: Int,
    val remarks: String,
    val repeat_test: Boolean,
    val repeat_test_date: Any,
    val repeat_test_type_uuid: Int,
    val revision: Boolean,
    val sample_type_uuid: Int,
    val sari: Boolean,
    val smart_ration_number: String,
    val state_uuid: Int,
    val symptom_duration: Any,
    val symptoms: Any,
    val taluk_uuid: Int,
    val test_location: Any,
    val test_result: Boolean,
    val travel_history: Any,
    val travel_history_date: Any,
    val travel_history_to_date: Any,
    val treatment_category_uuid: Int,
    val treatment_plan_detail: Any,
    val treatment_plan_uuid: Int,
    val underline_medicine_condition_uuid: Int,
    val underline_medicine_details: Any,
    val uuid: Int,
    val village_uuid: Int,
    val weight: String
)

data class PatientTypeDetail(
    val code: String,
    val is_active: Boolean,
    val name: String,
    val uuid: Int
)

data class PatientVisit(
    val created_by: Int,
    val created_date: String,
    val department_details: DepartmentDetails,
    val department_uuid: Int,
    val facility_uuid: Int,
    val is_active: Boolean,
    val is_last_visit: Boolean,
    val is_mlc: Boolean,
    val modified_by: Int,
    val modified_date: String,
    val patient_type_uuid: Int,
    val patient_uuid: Int,
    val registered_date: String,
    val revision: Boolean,
    val session_uuid: Int,
    val speciality_department_uuid: Int,
    val unit_uuid: Any,
    val uuid: Int,
    val visit_number: String,
    val visit_type_uuid: Int
)

data class PeriodDetail(
    val code: String,
    val is_active: Boolean,
    val name: String,
    val uuid: Int
)

data class RelationDetails(
    val code: String,
    val name: String,
    val uuid: Int
)

class SalutationDetails(
)

data class StateDetails(
    val code: String,
    val name: String,
    val uuid: Int
)

class SuffixDetails(
)

data class TalukDetails(
    val code: String,
    val name: String,
    val uuid: Int
)

data class VillageDetails(
    val code: String,
    val name: String,
    val uuid: Int
)

data class DepartmentDetails(
    val code: String,
    val name: String,
    val uuid: Int
)