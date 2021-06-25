package com.hmis_tn.admin.ui.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.home.model.PatientDetailsReq
import com.hmis_tn.admin.ui.home.model.network.OpListReq
import com.hmis_tn.admin.ui.home.model.patientDetails.PatientDetail
import com.hmis_tn.admin.ui.home.model.patientDetails.PatientDetails
import com.hmis_tn.admin.ui.home.view_model.HomeViewModel
import com.hmis_tn.admin.utils.Constants
import com.hmis_tn.admin.utils.ProgressUtil
import kotlinx.android.synthetic.main.activity_patient_detailes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientDetailesActivity: AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private var patient_req:Int?=null

    private var adapter:PateintDetailsAdapter?=null
    private var listData:ArrayList<PatientDetail> = ArrayList<PatientDetail>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detailes)

        getBundleData()
        initializeViews()

        getPatientDetails()

    }

    private fun getBundleData() {

    }

    private fun initializeViews() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

    }

    private fun getPatientDetails(){

        //3289 78096
        val body = PatientDetailsReq(
        patientId=78096
        )

        val pref = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE)
        val authorization = "Bearer ${pref.getString(Constants.PREF_ACCESS_TOKEN, "")}"
        val userUuid = pref.getInt(Constants.PREF_USER_UUID, 0)

        homeViewModel.getPatientDetailes(this,authorization,userUuid,body ,
            object : Callback<PatientDetails> {
                override fun onResponse(call: Call<PatientDetails>, response: Response<PatientDetails>) {
                    ProgressUtil.dismissProgressDialog()
                    listData.clear()

                    println("success"+response.body())

                    loadDataToViews(response.body())
                }

                override fun onFailure(call: Call<PatientDetails>, t: Throwable) {
                    println("success"+t.localizedMessage)
                    ProgressUtil.dismissProgressDialog()


                }
            }

        )
    }
    private fun loadDataToViews(body: PatientDetails?) {
        listData.add(body?.responseContent?.patient_detail!!)

        adapter= PateintDetailsAdapter(this,listData)
        rvPatientDetails.adapter=adapter

        tvInstitute.setText(body?.responseContent?.first_name+"/"+body?.responseContent?.age+"/"+body?.responseContent?.gender_uuid+"\n"+
                body?.responseContent?.uhid+"/"+body?.responseContent?.patient_detail?.mobile)

        tvInstitute.setText(":"+body?.responseContent?.facility_details?.name)
        tvward.setText(":"+body?.responseContent?.facility_details?.name)
        tvbedno.setText(":"+body?.responseContent?.facility_details?.name)
    }
}