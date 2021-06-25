package com.hmis_tn.admin.ui.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.home.model.Institution
import kotlinx.android.synthetic.main.item_institutions.view.*

class InstitutionAdapter(
    private val list: ArrayList<Institution>,
    private val click: (Int, String, String) -> Unit
) : RecyclerView.Adapter<InstitutionAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_institutions, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val institution = list[position]

        initViews(holder, institution)
        listeners(holder, institution)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun initViews(holder: MyViewHolder, institution: Institution) {
        val count = (institution.patient_count_1 ?: 0) +
                (institution.patient_count_2 ?: 0) +
                (institution.patient_count_3 ?: 0)
        with(holder.itemView) {
            tvInstitution.text = "${institution.facility_category_name_1} - ($count)"

            populateTextViews(holder, institution, institution.gender_name_1)
            populateTextViews(holder, institution, institution.gender_name_2)
            populateTextViews(holder, institution, institution.gender_name_3)
        }
    }

    private fun listeners(holder: MyViewHolder, institution: Institution) {
        with(holder.itemView) {
            llInstitution.setOnClickListener {
                if (llGender.visibility == View.VISIBLE) {
                    llGender.visibility = View.GONE
                } else {
                    llGender.visibility = View.VISIBLE
                }
            }

            tvMale.setOnClickListener {
                val encounterTypeUuid =
                    if (institution.encounter_type_name_1?.equals("OP", true) == true) "1" else "2"
                click(institution.facility_category_uuid_1 ?: 0, "1", encounterTypeUuid)
            }

            tvFemale.setOnClickListener {
                val encounterTypeUuid =
                    if (institution.encounter_type_name_2?.equals("OP", true) == true) "1" else "2"
                click(institution.facility_category_uuid_2 ?: 0, "2", encounterTypeUuid)
            }

            tvTransgender.setOnClickListener {
                val encounterTypeUuid =
                    if (institution.encounter_type_name_3?.equals("OP", true) == true) "1" else "2"
                click(institution.facility_category_uuid_3 ?: 0, "3", encounterTypeUuid)
            }
        }
    }

    private fun populateTextViews(
        holder: MyViewHolder,
        institution: Institution,
        genderName: String?
    ) {
        with(holder.itemView) {
            val maleCount = getCount(institution, "male")
            val femaleCount = getCount(institution, "female")
            val transgenderCount = getCount(institution, "transgender")

            when {
                genderName.equals("male", true) -> {
                    tvMale.text = "Male ($maleCount)"
                }
                genderName.equals("female", true) -> {
                    tvFemale.text = "Female ($femaleCount)"
                }
                genderName.equals("transgender", true) -> {
                    tvTransgender.text = "Transgender ($transgenderCount)"
                }
            }
        }
    }

    private fun getCount(institution: Institution, gender: String): Int {
        return when {
            institution.gender_name_1?.equals(gender, true) == true -> {
                institution.patient_count_1 ?: 0
            }
            institution.gender_name_2?.equals(gender, true) == true -> {
                institution.patient_count_2 ?: 0
            }
            institution.gender_name_3?.equals(gender, true) == true -> {
                institution.patient_count_3 ?: 0
            }
            else -> 0
        }
    }
}