package com.example.gaojunhui.textworld.kotlin

import android.app.Activity
import android.widget.Toast

/**
 * Created by gaojunhui on 2017/6/16.
 */
fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration)
}