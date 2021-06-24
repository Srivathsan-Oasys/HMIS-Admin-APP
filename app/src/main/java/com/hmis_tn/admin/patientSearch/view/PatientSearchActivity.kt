package com.hmis_tn.admin.patientSearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.hmis_tn.admin.R
import kotlinx.android.synthetic.main.activity_patient_search.*

class PatientSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_search)


        val animalList=getAnimalList()
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,android.R.layout.simple_spinner_item,
            animalList )
        tvInstitution.setAdapter(adapter)
        tvInstitution.onItemClickListener =
            AdapterView.OnItemClickListener { parent, arg1, position, id ->
                //TODO: You can your own logic.
            }
    }

    private fun setupAutoCompleteView() {


    }

    private fun getAnimalList(): ArrayList<String> {
        val animalList=ArrayList<String>()
        animalList.add("dog")
        animalList.add("cat")
        animalList.add("cow")
        animalList.add("elephant")
        animalList.add("snake")
        return animalList
    }
}