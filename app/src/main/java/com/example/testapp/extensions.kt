package com.example.testapp

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.show(){
    this.visibility = View.VISIBLE
}
fun View.hide(){
    this.visibility = View.GONE
}

fun Context.showToast(message:String,duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,message,duration).show()
}