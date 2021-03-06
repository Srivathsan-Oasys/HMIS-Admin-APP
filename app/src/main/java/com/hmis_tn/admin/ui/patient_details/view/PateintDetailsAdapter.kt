package com.hmis_tn.admin.ui.patient_details.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.patient_details.model.PatientDetail
import com.hmis_tn.admin.ui.patient_details.model.PatientVisit
import kotlinx.android.synthetic.main.adapter_patient_details.view.*

class PateintDetailsAdapter(
    private val context: Context,
    private var listData:List<PatientVisit>
):
    RecyclerView.Adapter<PateintDetailsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_patient_details, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val responseData=listData[position]

        holder.itemView.adapter_tvDate.text= responseData.registered_date
        holder.itemView.adapter_tvDoctorInfo.text=" "+"-"+responseData.department_details?.name
        holder.itemView.adapter_tvInfo.text=" "

    }

    override fun getItemCount(): Int {
       return listData.size
    }

}