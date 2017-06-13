package com.example.gaojunhui.textworld.kotlin

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.gaojunhui.textworld.R
import com.example.gaojunhui.textworld.R.id
import kotlinx.android.synthetic.main.activity_kotlin.kotlin_tv
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor

/**
 * Created by gaojunhui on 2017/6/7.
 */

class KotlinActivity : AppCompatActivity() {
    lateinit var kotlinTv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        /*kotlin-android-extensions*/
//        initByExtensions()
        /*原始的初始化view*/
//        init()
        /*auto*/
        initByAuto()
    }

    /**
     * 原始初始化方法
     */
    private fun init() {
        kotlinTv = findViewById(id.kotlin_tv) as TextView
        kotlinTv.text = "this is kotlin"
        kotlinTv.setTextColor(Color.BLACK)
    }

    /**
     * extensions方法初始化
     */
    private fun initByExtensions() {
        kotlin_tv.text = "this is kotlin"
        kotlin_tv.textColor = Color.parseColor("#aa00aa")
    }
    /**
     * auto方法初始化
     */
    private fun initByAuto(){
        kotlinTv=find(R.id.kotlin_tv)
        kotlinTv.text="this is kotlin"
        kotlinTv.textColor=Color.parseColor("#aa00aa")
    }
}
