package com.hmis_tn.admin.ui.home.view

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.home.model.OpListResp
import com.hmis_tn.admin.ui.home.model.OpListRespItem
import com.hmis_tn.admin.ui.home.view_model.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel

    private val list = ArrayList<OpListRespItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        listeners()

        getOpList()
    }

    private fun initViews() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        rvInstitutions?.layoutManager = LinearLayoutManager(this)
        val adapter = InstitutionAdapter(list)
        rvInstitutions?.adapter = adapter
    }

    private fun listeners() {
        tvOp.setOnClickListener {
            selectButton(tvOp)
        }

        tvIp.setOnClickListener {

        }
    }

    private fun selectButton(tv: TextView) {
        tvOp.setBackgroundColor(Color.WHITE)
        tvOp.setTextColor(Color.BLACK)
        tvIp.setBackgroundColor(Color.WHITE)

    }

    private fun getOpList() {
        homeViewModel.getOpList(this, callback = object : Callback<OpListResp> {
            override fun onResponse(call: Call<OpListResp>, response: Response<OpListResp>) {

            }

            override fun onFailure(call: Call<OpListResp>, t: Throwable) {

            }
        })
    }
}