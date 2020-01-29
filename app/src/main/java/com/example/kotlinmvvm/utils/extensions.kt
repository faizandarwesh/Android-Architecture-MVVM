package com.example.kotlinmvvm.utils

import android.content.Context
import android.widget.Toast

private var toast: Toast? = null

fun Context.showToast(message: String){
    if (toast != null)
        toast!!.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast!!.show()
}