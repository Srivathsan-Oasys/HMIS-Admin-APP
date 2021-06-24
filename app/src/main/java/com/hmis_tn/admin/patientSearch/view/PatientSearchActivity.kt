package com.hmis_tn.admin.patientSearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.hmis_tn.admin.R
import com.hmis_tn.admin.patientSearch.model.GenderResponse
import kotlinx.android.synthetic.main.activity_patient_search.*

class PatientSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_search)


        val items = listOf("Male", "Female", "Transgender")
        val genderAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, items)
        (spinnerGender as? AutoCompleteTextView)?.setAdapter(genderAdapter)

        val dististitems = listOf("chennai", "bfbhf", "Transgender")
        val distictAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, dististitems)
        (spinnerDistict as? AutoCompleteTextView)?.setAdapter(distictAdapter)

        val inistutionSpinner = listOf("chennai", "bfbhf", "Transgender")
        val inistutionAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, inistutionSpinner)
        (spinnerInstitution as? AutoCompleteTextView)?.setAdapter(inistutionAdapter)

        var responseContents :ArrayList<GenderResponse> = ArrayList()

        val responseContentAdapter = GenderListAdapter(this, R.layout.row_chief_complaint_search_result, responseContents)
        spinnerGender!!.threshold = 1
        spinnerGender!!.setAdapter(responseContentAdapter)
        spinnerGender!!.showDropDown()
        spinnerGender!!.setOnItemClickListener { parent, _, pos, id ->
            val selectedPoi = parent.adapter.getItem(pos) as GenderResponse?



        }


//        {"pageNo":0,"paginationSize":10,"sortField":"name","sortOrder":"DESC","codename":"001","is_active":1,"search":"Superhmis"}



    }

}