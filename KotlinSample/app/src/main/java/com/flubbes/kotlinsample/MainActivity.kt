package com.flubbes.kotlinsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

     lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById<View>(R.id.tv) as TextView
        tv.setOnClickListener { Toast.makeText(applicationContext, "works", Toast.LENGTH_SHORT).show() }
    }
}
