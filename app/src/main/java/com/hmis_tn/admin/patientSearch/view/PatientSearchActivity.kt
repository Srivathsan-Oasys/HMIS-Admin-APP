package com.hmis_tn.admin.patientSearch.view

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.hmis_tn.admin.R
import com.hmis_tn.admin.patientSearch.model.GenderListResponseModel
import com.hmis_tn.admin.patientSearch.model.GenderReq
import com.hmis_tn.admin.patientSearch.model.GenderResponse
import com.hmis_tn.admin.patientSearch.view_model.PatientListViewModel
import com.hmis_tn.admin.ui.login.model.LoginResp
import com.hmis_tn.admin.ui.login.view_model.LoginViewModel
import com.hmis_tn.admin.utils.Constants
import com.hmis_tn.admin.utils.ProgressUtil
import kotlinx.android.synthetic.main.activity_patient_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class PatientSearchActivity : AppCompatActivity() {


    private lateinit var patientListViewModel: PatientListViewModel
    private var pref: SharedPreferences? = null

    private var mYear: Int? = null
    private var mMonth: Int? = null
    private var mDay: Int? = null
    private var fromDate: String = ""
    private var toDate: String = ""

    private var fromDateRev: String = ""
    private var toDateRev: String = ""

    var cal = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_search)


        initView()

        initLisitor()

/*
        val items = listOf("Male", "Female", "Transgender")
        val genderAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, items)
        (spinnerGender as? AutoCompleteTextView)?.setAdapter(genderAdapter)
*/

        val dististitems = listOf("chennai", "bfbhf", "Transgender")
        val distictAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, dististitems)
        (spinnerDistict as? AutoCompleteTextView)?.setAdapter(distictAdapter)

        val inistutionSpinner = listOf("chennai", "bfbhf", "Transgender")
        val inistutionAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, inistutionSpinner)
        (spinnerInstitution as? AutoCompleteTextView)?.setAdapter(inistutionAdapter)

        var responseContents :ArrayList<GenderResponse> = ArrayList()




//        {"pageNo":0,"paginationSize":10,"sortField":"name","sortOrder":"DESC","codename":"001","is_active":1,"search":"Superhmis"}



    }

    private fun initLisitor() {

        calendarEditText!!.setOnClickListener {

            Toast.makeText(this, "Select Start Date", Toast.LENGTH_LONG).show()
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    Toast.makeText(this, "Select End Date", Toast.LENGTH_LONG).show()

                    fromDate = String.format(
                        "%02d",
                        dayOfMonth
                    ) + "-" + String.format("%02d", monthOfYear + 1) + "-" + year

                    fromDateRev = year.toString() + "-" + String.format(
                        "%02d",
                        monthOfYear + 1
                    ) + "-" + String.format(
                        "%02d",
                        dayOfMonth
                    )+" 00:00:00"


                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)


                    val dateoickDialog = DatePickerDialog(
                        this,
                        DatePickerDialog.OnDateSetListener { view, year1, month1, dayOfMonth1 ->

                            toDate = String.format(
                                "%02d",
                                dayOfMonth1
                            ) + "-" + String.format("%02d", month1 + 1) + "-" + year1

                            toDateRev = year1.toString() + "-" + String.format(
                                "%02d",
                                month1 + 1
                            ) + "-" + String.format(
                                "%02d",
                                dayOfMonth1
                            )+ " 23:59:59"

                            calendarEditText!!.setText(fromDate + "-" + toDate)

                            Log.i("fromdate",fromDateRev)
                            Log.i("todate",toDateRev)

                        },
                        mYear!!,
                        mMonth!!,
                        mDay!!
                    )

                    dateoickDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis

                    dateoickDialog.datePicker.minDate = cal.timeInMillis

                    dateoickDialog.show()


                }, mYear!!, mMonth!!, mDay!!
            )

            datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis

            datePickerDialog.show()

        }
    }

    private fun initView() {
        patientListViewModel = ViewModelProvider(this)[PatientListViewModel::class.java]

        pref = getSharedPreferences(
            Constants.SHARED_PREFERENCE_NAME,
            MODE_PRIVATE
        )
        getGenderData()
    }

    private fun getGenderData() {

        val body= GenderReq()

       val token: String? = "Bearer "+pref?.getString(Constants.PREF_ACCESS_TOKEN,"s")
       val userId: Int? = pref?.getInt(Constants.PREF_USER_UUID,0)

        patientListViewModel.getGender(this,body,token,userId, object : Callback<GenderListResponseModel> {
            override fun onResponse(
                call: Call<GenderListResponseModel>,
                response: Response<GenderListResponseModel>
            ) {

                ProgressUtil.dismissProgressDialog()

                response.body()?.let { genderListResponseModel ->

                    if(genderListResponseModel.statusCode==200){

                        setGenderData(genderListResponseModel.responseContents)

                    }
                }
            }

            override fun onFailure(call: Call<GenderListResponseModel>, t: Throwable) {
                ProgressUtil.dismissProgressDialog()
                t.printStackTrace()
            }
        })

    }


    private fun setGenderData(responseContents: List<GenderResponse>) {

        val responseContentAdapter = GenderListAdapter(this, R.layout.row_chief_complaint_search_result, responseContents as ArrayList<GenderResponse>)
        spinnerGender!!.threshold = 1
        spinnerGender!!.setAdapter(responseContentAdapter)
        spinnerGender!!.setOnItemClickListener { parent, _, pos, id ->
            val selectedPoi = parent.adapter.getItem(pos) as GenderResponse?

            spinnerGender.setText(selectedPoi?.name)

            Log.i("response",""+selectedPoi?.name)


        }


    }

}