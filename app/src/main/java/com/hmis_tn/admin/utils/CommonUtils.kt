package com.hmis_tn.admin.utils

import android.os.Build
import android.util.Log
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CommonUtils {

    fun encrypt(value: String?): String {
        var encode: String? = null
        val iv = IvParameterSpec(Constants.IV.toByteArray(Charsets.UTF_8))

        val skeySpec = SecretKeySpec(
            Constants.ENCRYPT_KEY.toByteArray(Charsets.UTF_8),
            Constants.ALGORITHAM
        )

        val cipher = Cipher.getInstance(Constants.AES_SELECTED_MODE)
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
        val encrypted = cipher?.doFinal(value?.toByteArray())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            encode = Base64.getEncoder().encodeToString(encrypted)
        } else {
            encode = android.util.Base64.encodeToString(encrypted, android.util.Base64.DEFAULT)
            encode = encode.replace("\n", "")

        }
        Log.i("", "" + encode)
        return encode!!
    }
}