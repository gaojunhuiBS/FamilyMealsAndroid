package com.example.gaojunhui.textworld.kotlin

/**
 * Created by gaojunhui on 2017/6/20.
 */
interface MyInterface {
    val name: String
    val nameImp:String
    get() = "foo"
    fun boy()
    fun girl(){}
}