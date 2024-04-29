package com.gallordev.myapplication.utils

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout

object Extensions {
    fun TextInputLayout.getText(): String {
        return this.editText?.text?.trim().toString()
    }

    fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

}