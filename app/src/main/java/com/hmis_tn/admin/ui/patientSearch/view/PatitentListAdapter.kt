package com.hmis_tn.admin.ui.patientSearch.view


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.patientSearch.model.PatientData

class PatitentListAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<PatitentListAdapter.MyViewHolder>() {

    private var responseContent: ArrayList<PatientData?>? = ArrayList()
    private var onItemClickListener: OnItemClickListener? = null
    private var isLoadingAdded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_patient, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val responseContent = responseContent!![position]

        if (responseContent?.title_name != null && responseContent.title_name != "null") {

            if (responseContent.last_name != null && responseContent.last_name != "null") {
                holder.patientNameTextView.text = responseContent.title_name +
                        responseContent.first_name + " " + responseContent.last_name + "/" + responseContent.ageperiod

            } else {
                holder.patientNameTextView.text = responseContent.title_name +
                        responseContent.first_name + "/" + responseContent.ageperiod
            }
        } else if (responseContent?.last_name != null && responseContent.last_name != "null") {
            holder.patientNameTextView.text =
                responseContent.first_name + " " + responseContent.last_name + "/" + responseContent.ageperiod
        } else {
            holder.patientNameTextView.text =
                responseContent?.first_name + "/" + responseContent?.ageperiod
        }
        holder.pinTextView.text = responseContent?.uhid
        holder.phoneNumberTextView.text = responseContent?.mobile
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(responseContent, position)
        }

    }

    override fun getItemCount(): Int {
        return responseContent!!.size
    }

    inner class MyViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val patientNameTextView: TextView = itemView.findViewById(R.id.patientNameTextView)
        internal val pinTextView: TextView = itemView.findViewById(R.id.pinTextView)
        internal val phoneNumberTextView: TextView = itemView.findViewById(R.id.phoneNumberTextView)

    }

    fun addAll(responseContent: List<PatientData?>?) {
        this.responseContent?.addAll(responseContent!!)
        notifyDataSetChanged()
    }


    interface OnItemClickListener {
        fun onItemClick(responseContent: PatientData?, position: Int)
    }


    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun clearAll() {
        this.responseContent?.clear()
        notifyDataSetChanged()
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
//        add(ResponseContent())
    }

    fun add(r: PatientData) {
        responseContent!!.add(r)
        notifyItemInserted(responseContent!!.size - 1)
    }

    fun getItem(position: Int): PatientData? {
        return responseContent!![position]
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

    }


}

