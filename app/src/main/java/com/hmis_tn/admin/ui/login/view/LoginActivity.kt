package com.hmis_tn.admin.ui.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.home.view.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        listeners()
    }

    private fun initViews() {

    }

    private fun listeners() {
        btnLogin?.setOnClickListener {
            if (etUserName?.text?.toString() == "admin" && etPwd.text.toString() == "admin") {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val sb = Snackbar.make(
                    coordinatorLayout,
                    "Please check the username and password",
                    Snackbar.LENGTH_SHORT
                )
                sb.show()
            }
        }
    }
}