package com.hmis_tn.admin.ui.login.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hmis_tn.admin.R
import com.hmis_tn.admin.ui.home.view.HomeActivity
import com.hmis_tn.admin.ui.home.view.PatientDetailesActivity
import com.hmis_tn.admin.ui.login.model.LoginReq
import com.hmis_tn.admin.ui.login.model.LoginResp
import com.hmis_tn.admin.ui.login.view_model.LoginViewModel
import com.hmis_tn.admin.utils.CommonUtils
import com.hmis_tn.admin.utils.Constants
import com.hmis_tn.admin.utils.ProgressUtil
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        listeners()
    }

    private fun initViews() {
        pref = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun listeners() {
        btnLogin?.setOnClickListener {
            if (etUserName.text?.isNotEmpty() == true && etPwd.text?.isNotEmpty() == true) {
                postLogin(etUserName?.text?.toString(), etPwd?.text?.toString())
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

    private fun postLogin(name: String?, pwd: String?) {
        val body = LoginReq(username = name, password = CommonUtils.encrypt(pwd))
        loginViewModel.postLogin(this, body, object : Callback<LoginResp> {
            override fun onResponse(call: Call<LoginResp>, response: Response<LoginResp>) {
                ProgressUtil.dismissProgressDialog()
                response.body()?.let { loginResp ->
                    if (loginResp.statusCode == 200) {
                        pref?.edit()?.let { editor ->
                            editor.putString(
                                Constants.PREF_ACCESS_TOKEN,
                                loginResp.responseContents?.userDetails?.access_token ?: ""
                            )
                            editor.putInt(
                                Constants.PREF_USER_UUID,
                                loginResp.responseContents?.userDetails?.uuid ?: 0
                            )
                            editor.apply()
                        }

                        val intent = Intent(applicationContext, PatientDetailesActivity::class.java)
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

            override fun onFailure(call: Call<LoginResp>, t: Throwable) {
                ProgressUtil.dismissProgressDialog()
                t.printStackTrace()
            }
        })
    }
}