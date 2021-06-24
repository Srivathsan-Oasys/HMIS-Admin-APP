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
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmis_tn.admin.R
import com.hmis_tn.admin.patientSearch.model.*
import com.hmis_tn.admin.patientSearch.view_model.PatientListViewModel
import com.hmis_tn.admin.ui.home.view.InstitutionAdapter
import com.hmis_tn.admin.ui.login.model.LoginResp
import com.hmis_tn.admin.ui.login.view_model.LoginViewModel
import com.hmis_tn.admin.utils.Constants
import com.hmis_tn.admin.utils.ProgressUtil
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_patient_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
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

    var facility_category_id = "1"
    var gender_id :String= "2"
    var from_datetime:String = ""
    var to_datetime :String= ""
    var encounter_type_id :String= "1"
    var token: String?=""
    var userId: Int?=0

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


    }

    private fun initLisitor() {

        calendarEditText!!.setOnClickListener {
            Toast.makeText(this, "Select Start Date", Toast.LENGTH_SHORT).show()
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    Toast.makeText(this, "Select End Date", Toast.LENGTH_SHORT).show()

                    fromDate = String.format(
                        "%02d",
                        dayOfMonth
                    ) + "-" + String.format("%02d", monthOfYear + 1) + "-" + year

                    from_datetime = year.toString() + "-" + String.format(
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

                            to_datetime = year1.toString() + "-" + String.format(
                                "%02d",
                                month1 + 1
                            ) + "-" + String.format(
                                "%02d",
                                dayOfMonth1
                            )+ " 23:59:59"

                            calendarEditText!!.setText(fromDate + "-" + toDate)

                            getPatientList()
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

        token = "Bearer "+pref?.getString(Constants.PREF_ACCESS_TOKEN,"s")
        userId = pref?.getInt(Constants.PREF_USER_UUID,0)


        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        calendarEditText!!.setText("""${formatter.format(Date())}-${formatter.format(Date())}""")
        from_datetime=sdf.format(Date())+" 00:00:00"
        to_datetime=sdf.format(Date())+ " 23:59:59"
//        getGenderData()
        getPatientList()
    }

    private fun getPatientList() {
        var body=PatitentListRequest(
            facility_category_id = facility_category_id,
            gender_id = gender_id,
            from_datetime = from_datetime,
            to_datetime = to_datetime,
            encounter_type_id =  encounter_type_id

        )

        patientListViewModel.getPatitentList(this,body,token,userId, object : Callback<PatientListResponseModel>{
            override fun onResponse(
                call: Call<PatientListResponseModel>,
                response: Response<PatientListResponseModel>
            ) {

                ProgressUtil.dismissProgressDialog()

                response.body()?.let { it ->

                    setAdapter(it.responseContents)
                }

            }

            override fun onFailure(call: Call<PatientListResponseModel>, t: Throwable) {
                ProgressUtil.dismissProgressDialog()
                t.printStackTrace()
            }
        })


    }

    private fun setAdapter(responseContents: List<PatientData>) {

        patientListData?.layoutManager = LinearLayoutManager(this@PatientSearchActivity)
        val adapter = PatitentListAdapter(this@PatientSearchActivity)
        patientListData?.adapter = adapter
        adapter.addAll(responseContents)

        tvListPatientCount.text = "Total Patient Count (${responseContents.size!!})"

        adapter.setOnItemClickListener(object :PatitentListAdapter.OnItemClickListener{
            override fun onItemClick(responseContent: PatientData?, position: Int) {
                // next page


            }
        })

    }

    private fun getGenderData() {

        val body= GenderReq()

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