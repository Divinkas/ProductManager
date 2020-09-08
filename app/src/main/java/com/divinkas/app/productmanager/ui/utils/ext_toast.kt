package com.divinkas.app.productmanager.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.divinkas.app.productmanager.R

data class ToastWrapper(val context: Context, var text: String = "", var length: Int = Toast.LENGTH_SHORT)

fun ToastWrapper.text(block: () -> Int) {
    this.text = context.getString(block())
}

fun ToastWrapper.lengthLong() {
    this.length = Toast.LENGTH_LONG
}

@SuppressLint("WrongConstant")
fun toast(context: Context, block: ToastWrapper.() -> Unit) {
    val wrapper = ToastWrapper(context).apply(block)
    Toast.makeText(context, wrapper.text, wrapper.length).show()
}

@SuppressLint("InflateParams")
fun showRectangleToast(context: Context, text: String) {
    val view = LayoutInflater.from(context).inflate(R.layout.toast_rectangle, null)
    view.findViewById<TextView>(R.id.toast_text).text = text

    val toast = Toast(context)
    toast.setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 40)
    toast.duration = Toast.LENGTH_SHORT
    toast.view = view
    toast.show()
}

@SuppressLint("InflateParams")
fun showRectangleToast(context: Context, text: Int) {
    val view = LayoutInflater.from(context).inflate(R.layout.toast_rectangle, null)
    view.findViewById<TextView>(R.id.toast_text).text = context.getString(text)

    val toast = Toast(context)
    toast.setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 40)
    toast.duration = Toast.LENGTH_SHORT
    toast.view = view
    toast.show()
}
