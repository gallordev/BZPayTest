package com.gallordev.myapplication.utils

import com.google.android.material.textfield.TextInputLayout

object Extensions {
    fun TextInputLayout.getText(): String {
        return this.editText?.text?.trim().toString()
    }

}