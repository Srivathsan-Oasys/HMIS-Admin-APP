package com.hmis_tn.admin.ui.home.view

import android.content.Intent
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmis_tn.admin.R
import com.hmis_tn.admin.patientSearch.view.PatientSearchActivity
import com.hmis_tn.admin.ui.home.model.Institution
import com.hmis_tn.admin.ui.home.model.network.InstitutionListReq
import com.hmis_tn.admin.ui.home.model.network.InstitutionListResp
import com.hmis_tn.admin.ui.home.model.network.ResponseContent
import com.hmis_tn.admin.ui.home.view_model.HomeViewModel
import com.hmis_tn.admin.utils.Constants
import com.hmis_tn.admin.utils.ProgressUtil
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel

    val displayList = ArrayList<Institution>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        listeners()

        getInstitutionList("1")
    }

    private fun initViews() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        tvPatientCount.text = "Total Patient Count - (0)"

        rvInstitutions?.layoutManager = LinearLayoutManager(this)
        val adapter =
            InstitutionAdapter(displayList) { facilityCategoryId, genderId, encounterTypeId ->
                val intent = Intent(this, PatientSearchActivity::class.java)
                val bundle = Bundle()
                bundle.putString(Constants.BUNDLE_FACILITY_CATEGORY_ID, facilityCategoryId)
                bundle.putString(Constants.BUNDLE_GENDER_ID, genderId)
                bundle.putString(Constants.BUNDLE_ENCOUNTER_TYPE_ID, encounterTypeId)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        rvInstitutions?.adapter = adapter
    }

    private fun listeners() {
        tvOp.setOnClickListener {
            displayList.clear()
            rvInstitutions?.adapter?.notifyDataSetChanged()
            selectButton(tvOp)
            getInstitutionList("1")
        }

        tvIp.setOnClickListener {
            displayList.clear()
            rvInstitutions?.adapter?.notifyDataSetChanged()
            selectButton(tvIp)
            getInstitutionList("2")
        }
    }

    private fun selectButton(tv: TextView) {
        tvOp.setBackgroundColor(Color.TRANSPARENT)
        tvIp.setBackgroundColor(Color.TRANSPARENT)
        tvOp.setTextColor(getDefaultTextColor())
        tvIp.setTextColor(getDefaultTextColor())

        tv.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
        tv.setTextColor(Color.WHITE)
    }

    private fun getDefaultTextColor(): Int {
        val typedValue = TypedValue()
        val theme: Resources.Theme = this.theme
        theme.resolveAttribute(android.R.attr.textColorPrimary, typedValue, true)
        val arr: TypedArray = this.obtainStyledAttributes(
            typedValue.data,
            intArrayOf(android.R.attr.textColorPrimary)
        )
        return arr.getColor(0, -1)
    }

    private fun populateInstitution(
        temp: ArrayList<ResponseContent>
    ): Institution {
        val tempInstitution = Institution()
        if (temp.size > 0) {
            tempInstitution.encounter_type_name_1 = temp[0].encounter_type_name
            tempInstitution.facility_category_name_1 =
                temp[0].facility_category_name
            tempInstitution.facility_category_uuid_1 =
                temp[0].facility_category_uuid
            tempInstitution.gender_name_1 = temp[0].gender_name
            tempInstitution.gender_uuid_1 = temp[0].gender_uuid
            tempInstitution.patient_count_1 = temp[0].patient_count
        }

        if (temp.size > 1) {
            tempInstitution.encounter_type_name_2 = temp[1].encounter_type_name
            tempInstitution.facility_category_name_2 =
                temp[1].facility_category_name
            tempInstitution.facility_category_uuid_2 =
                temp[1].facility_category_uuid
            tempInstitution.gender_name_2 = temp[1].gender_name
            tempInstitution.gender_uuid_2 = temp[1].gender_uuid
            tempInstitution.patient_count_2 = temp[1].patient_count
        }

        if (temp.size > 2) {
            tempInstitution.encounter_type_name_3 = temp[2].encounter_type_name
            tempInstitution.facility_category_name_3 =
                temp[2].facility_category_name
            tempInstitution.facility_category_uuid_3 =
                temp[2].facility_category_uuid
            tempInstitution.gender_name_3 = temp[2].gender_name
            tempInstitution.gender_uuid_3 = temp[2].gender_uuid
            tempInstitution.patient_count_3 = temp[2].patient_count
        }

        return tempInstitution
    }

    private fun getInstitutionList(encounterTypeId: String) {
        val body = InstitutionListReq(
            encounter_type_id = encounterTypeId,
            from_datetime = "2021-06-24 00:00:00",
            to_datetime = "2021-06-24 23:59:59"
        )
        val pref = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE)
        val authorization = "Bearer ${pref.getString(Constants.PREF_ACCESS_TOKEN, "")}"
        val userUuid = pref.getInt(Constants.PREF_USER_UUID, 0)
        homeViewModel.getOpList(this, authorization, userUuid, body, institutionCallback)
    }

    private val institutionCallback = object : Callback<InstitutionListResp> {
        override fun onResponse(
            call: Call<InstitutionListResp>,
            response: Response<InstitutionListResp>
        ) {
            ProgressUtil.dismissProgressDialog()
            if (response.body()?.code == 200) {
                response.body()?.responseContents?.let {
                    val temp = ArrayList<ResponseContent>()
                    var name =
                        if (it.isNotEmpty())
                            it[0].facility_category_name
                        else
                            ""
                    var institution: Institution
                    var totalPatientCount = 0
                    it.forEach { responseContent ->
                        totalPatientCount += responseContent.patient_count ?: 0
                        if (responseContent.facility_category_name?.equals(name, true) != true) {
                            institution = populateInstitution(temp)

                            institution.let { t -> displayList.add(t) }
                            temp.clear()
                            name = responseContent.facility_category_name
                        }

                        temp.add(responseContent)
                    }

                    institution = populateInstitution(temp)
                    if ((institution.patient_count_1 ?: 0) +
                        (institution.patient_count_2 ?: 0) +
                        (institution.patient_count_3 ?: 0) > 0
                    ) {
                        institution.let { t -> displayList.add(t) }
                    }
                    rvInstitutions?.adapter?.notifyDataSetChanged()
                    tvPatientCount.text = "Total Patient Count - ($totalPatientCount)"
                }
            }
        }

        override fun onFailure(call: Call<InstitutionListResp>, t: Throwable) {
            ProgressUtil.dismissProgressDialog()
            t.printStackTrace()
        }
    }
}