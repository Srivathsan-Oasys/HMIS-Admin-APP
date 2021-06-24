package com.hmis_tn.admin.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.hmis_tn.admin.R

object ProgressUtil {

    private var dialog: Dialog? = null

    fun startProgressDialog(context: Context?) {
        try {
            if (dialog != null && dialog!!.isShowing) return
            dialog = Dialog(context!!)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            if (dialog!!.window != null) {
                dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            }
            dialog!!.setContentView(R.layout.custom_progress_dialog)
            dialog!!.setCancelable(true)
            dialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissProgressDialog() {
        if (dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }
}