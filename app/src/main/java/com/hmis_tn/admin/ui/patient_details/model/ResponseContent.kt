package com.hmis_tn.admin.ui.patient_details.model

data class ResponseContent(
    val age: Int? = 0,
    val application_identifier: Any? = Any(),
    val application_request_type_uuid: Int? = 0,
    val application_type_uuid: Int? = 0,
    val application_uuid: Any? = Any(),
    val block_details: BlockDetails? = BlockDetails(),
    val blood_group_details: BloodGroupDetails? = BloodGroupDetails(),
    val blood_group_uuid: Int? = 0,
    val category_details: CategoryDetails? = CategoryDetails(),
    val country_details: CountryDetails? = CountryDetails(),
    val covid_patient_details: Any? = Any(),
    val created_by: Int? = 0,
    val created_date: String? = "",
    val district_details: DistrictDetails? = DistrictDetails(),
    val dob: String? = "",
    val facility_details: FacilityDetails? = FacilityDetails(),
    val first_name: String? = "",
    val gender_details: GenderDetails? = GenderDetails(),
    val gender_uuid: Int? = 0,
    val id_proof_details: IdProofDetails? = IdProofDetails(),
    val income_details: IncomeDetails? = IncomeDetails(),
    val is_active: Boolean? = false,
    val is_adult: Boolean? = false,
    val is_dob_auto_calculate: Boolean? = false,
    val is_maternity: Boolean? = false,
    val is_op_backentry: Boolean? = false,
    val last_name: String? = "",
    val marital_details: MaritalDetails? = MaritalDetails(),
    val maternity_details: Any? = Any(),
    val middle_name: String? = "",
    val modified_by: Int? = 0,
    val modified_date: String? = "",
    val occupation_details: OccupationDetails? = OccupationDetails(),
    val old_pin: Any? = Any(),
    val package_uuid: Int? = 0,
    val para_1: Any? = Any(),
    val para_2: String? = "",
    val para_3: String? = "",
    val para_4: String? = "",
    val para_5: String? = "",
    val patient_condition_details: List<Any>? = listOf(),
    val patient_detail: PatientDetail? = PatientDetail(),
    val patient_scheme_no: Any? = Any(),
    val patient_scheme_uuid: Int? = 0,
    val patient_specimen_details: List<Any>? = listOf(),
    val patient_symptoms: List<Any>? = listOf(),
    val patient_type_detail: PatientTypeDetail? = PatientTypeDetail(),
    val patient_type_uuid: Int? = 0,
    val patient_visits: List<PatientVisit>? = listOf(),
    val period_detail: PeriodDetail? = PeriodDetail(),
    val period_uuid: Int? = 0,
    val professional_title_uuid: Int? = 0,
    val registered_date: String? = "",
    val registred_facility_uuid: Int? = 0,
    val relation_details: RelationDetails? = RelationDetails(),
    val revision: Boolean? = false,
    val salutation_details: SalutationDetails? = SalutationDetails(),
    val state_details: StateDetails? = StateDetails(),
    val status: Boolean? = false,
    val suffix_details: SuffixDetails? = SuffixDetails(),
    val suffix_uuid: Int? = 0,
    val taluk_details: TalukDetails? = TalukDetails(),
    val tat_end_time: String? = "",
    val tat_start_time: String? = "",
    val title_uuid: Int? = 0,
    val uhid: String? = "",
    val uuid: Int? = 0,
    val village_details: VillageDetails? = VillageDetails()
)