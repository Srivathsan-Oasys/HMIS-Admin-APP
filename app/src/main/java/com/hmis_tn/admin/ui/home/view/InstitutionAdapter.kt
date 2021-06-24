package com.hmis_tn.admin.ui.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.home.model.OpListRespItem
import kotlinx.android.synthetic.main.item_institutions.view.*

class InstitutionAdapter(
    private val list: ArrayList<OpListRespItem>
) : RecyclerView.Adapter<InstitutionAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_institutions, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val opListRespItem = list[position]


        initViews(holder, opListRespItem)
        listeners(holder, opListRespItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun initViews(holder: MyViewHolder, opListRespItem: OpListRespItem) {
        with(holder.itemView) {
            tvInstitution.text = opListRespItem.facility_category_name
        }
    }

    private fun listeners(holder: MyViewHolder, opListRespItem: OpListRespItem) {
        with(holder.itemView) {
            llInstitution.setOnClickListener {

            }
        }
    }
}