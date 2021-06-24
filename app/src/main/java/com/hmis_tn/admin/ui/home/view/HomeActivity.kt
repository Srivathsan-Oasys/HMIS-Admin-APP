package com.hmis_tn.admin.ui.home.view

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
import com.hmis_tn.admin.ui.home.model.Institution
import com.hmis_tn.admin.ui.home.model.network.OpListReq
import com.hmis_tn.admin.ui.home.model.network.OpListResp
import com.hmis_tn.admin.ui.home.view_model.HomeViewModel
import com.hmis_tn.admin.utils.Constants
import com.hmis_tn.admin.utils.ProgressUtil
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel

    val displayList = ArrayList<ArrayList<Institution>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        listeners()

        getOpList()
    }

    private fun initViews() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        rvInstitutions?.layoutManager = LinearLayoutManager(this)
        val adapter = InstitutionAdapter(displayList)
        rvInstitutions?.adapter = adapter
    }

    private fun listeners() {
        tvOp.setOnClickListener {
            displayList.clear()
            rvInstitutions?.adapter?.notifyDataSetChanged()
            selectButton(tvOp)
            getOpList()
        }

        tvIp.setOnClickListener {
            displayList.clear()
            rvInstitutions?.adapter?.notifyDataSetChanged()
            selectButton(tvIp)
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

    private fun getOpList() {
        val body = OpListReq(
            encounter_type_id = "1",
            from_datetime = "2021-06-24 00:00:00",
            to_datetime = "2021-06-24 23:59:59"
        )
        val pref = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE)
        val authorization = "Bearer ${pref.getString(Constants.PREF_ACCESS_TOKEN, "")}"
        val userUuid = pref.getInt(Constants.PREF_USER_UUID, 0)
        homeViewModel.getOpList(
            this,
            authorization,
            userUuid,
            body,
            object : Callback<OpListResp> {
                override fun onResponse(call: Call<OpListResp>, response: Response<OpListResp>) {
                    ProgressUtil.dismissProgressDialog()
                    if (response.body()?.code == 200) {
                        response.body()?.responseContents?.let {
                            val temp = ArrayList<Institution>()
                            var name =
                                if (it.isNotEmpty())
                                    it[0].facility_category_name
                                else
                                    ""
                            it.forEach { responseContent ->
                                if (responseContent.facility_category_name != name) {
                                    displayList.add(temp)
                                    temp.clear()
                                    name = responseContent.facility_category_name
                                }
                                temp.add(
                                    Institution(
                                        encounter_type_name = responseContent.encounter_type_name,
                                        facility_category_name = responseContent.facility_category_name,
                                        facility_category_uuid = responseContent.facility_category_uuid,
                                        gender_name = responseContent.gender_name,
                                        gender_uuid = responseContent.gender_uuid,
                                        patient_count = responseContent.patient_count
                                    )
                                )
                            }
                            displayList.add(temp)
                            rvInstitutions?.adapter?.notifyDataSetChanged()
                        }
                    }
                }

                override fun onFailure(call: Call<OpListResp>, t: Throwable) {
                    ProgressUtil.dismissProgressDialog()
                    t.printStackTrace()
                }
            })
    }
}