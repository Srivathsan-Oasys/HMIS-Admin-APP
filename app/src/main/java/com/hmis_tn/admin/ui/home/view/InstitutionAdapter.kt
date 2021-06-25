package com.hmis_tn.admin.ui.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.home.model.Institution
import com.hmis_tn.admin.utils.CommonUtils
import kotlinx.android.synthetic.main.item_institutions.view.*

class InstitutionAdapter(
    private val list: ArrayList<Institution>
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
//                    llGender?.animate()?.translationY(llGender.height.toFloat())
//                    llGender?.animate()?.alpha(1.0f)?.duration = 2000
                } else {
                    llGender.visibility = View.VISIBLE
//                    llGender?.animate()?.translationY(0F)
//                    llGender?.animate()?.alpha(0.0f)?.duration = 2000
                }

            }
        }
    }

    private fun populateTextViews(
        holder: MyViewHolder,
        institution: Institution,
        genderName: String?
    ) {
        with(holder.itemView) {
            when {
                genderName.equals("male", true) -> {
                    tvMale.text = "Male (${institution.patient_count_1})"
                }
                genderName.equals("female", true) -> {
                    tvFemale.text = "Female (${institution.patient_count_2})"
                }
                genderName.equals("transgender", true) -> {
                    tvTransgender.text = "Transgender (${institution.patient_count_3})"
                }
            }
        }
    }
}