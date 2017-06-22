package com.example.gaojunhui.textworld.kotlin

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.gaojunhui.textworld.R
import com.example.gaojunhui.textworld.R.id
import com.example.gaojunhui.textworld.toasty.Toasty
import com.example.gaojunhui.textworld.util.LogUtils
import kotlinx.android.synthetic.main.activity_kotlin.kotlin_tv
import kotlinx.android.synthetic.main.activity_kotlin.kt_btn
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import java.io.File
import javax.xml.datatype.Duration

/**
 * Created by gaojunhui on 2017/6/7.
 */

class KotlinActivity : AppCompatActivity() {
    lateinit var kotlinTv: TextView
    lateinit var kotlinBt: Button
    lateinit var person: Person
    //    lateinit val name:String//val:定义为不可修改的值;var：可以修改值的变量
//    lateinit var peoples:List<String>=ArrayList()//不需要new arrayList
//    lateinit val tuesday=date.isTusedaty();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        /*kotlin-android-extensions*/
//        initByExtensions()
        /*原始的初始化view*/
//        init()
        /*auto*/
        initByAuto()
        person = Person("小明", 20, "男");
        kt_btn.setOnClickListener {
            //click() //点击事件
//            toast(sum(1, 2).toString())//求和
//            kotlinTv.text = ifText(2, 3).toString()//if
//            kotlinTv.text = ifText2(6)
//            textNull2()
//            isText()
//            forText()
//            whenText()
//            downText()
//            noInText()
//            vars(1,2,3,4,5)
            val textInterface=TextInterface()
            toast(textInterface.nameImp)
        }
    }
    /**
     * 抽象类
     * 使用abstract来描述一个抽象类，
     * 抽象类里的方法如果不提供实现，
     * 则需要abstract来描述抽象方法
     * 抽象方法默认是open的
     */
//    abstract class A{
//        abstract fun f()
//    }
//    interface B{
//        open fun c(){
//            print("B")
//        }
//    }
//    class C :A, B{
//        override fun f() {
//
//        }
//
//        override fun c() {
//            super.c()
//        }
//    }
    /**
     * 接口继承
     */
    class TextInterface:MyInterface{
        override val name: String
            get() = TODO(
                    "not implemented") //To change initializer of created properties use File | Settings | File Templates.
        override val nameImp: String
            get() = super.nameImp
        override fun boy() {

        }
    }
    /**
     * vararg:函数的变长参数
     * 注：只有一个参数可以被标注为vararg
     */
    fun vars(vararg v:Int){
        for (vs in v){
            toast(vs.toString())
        }
    }
    /**
     * 方法的声明
     */
    fun textFun(b:Array<Byte>,off:Int=1,name:String="a"){

    }
    /**
     * lateinit:属性延迟初始化
     * 非空的属性在定义的时候必须初始化
     */
    lateinit var lateinitText:String
    /**
     *类
     * 1.如主构造函数没有注解或可见性说明
     *  则，constructor可以省略
     * 2.如果构造函数有注解或可见性说明，
     *  则，constructor是不可少的，并且注解
     *  应该在前
     *
     */
    class Persons constructor(name:String){

    }
    class Tree(var name:String){

    }
//    class Customer public inject constructor(name: String){
//
//    }
    /**
     * 范围外
     * !in
     */
    val noInText=1
    fun noInText(){
        var noIn=when(noInText){
//            !in 3..5->noInText.toString()
            0,1->"0-or-1"
            else->"3-5"
        }
        kotlinTv.text=noIn
    }
    /**
     * 降序
     * step:修改步长
     */
    val down=5 downTo 1//5,4,3,2,1
    val step=5 downTo 1 step 2
    fun downText(){
        for (a in step) {
            toast(a.toString())
            kotlinTv.text = a.toString()
        }
    }
    /**
     *when:替代swith,功能更强大
     * else，必须有，相当于default
     * in a..b:在什么范围（闭区间）
     */
    val we = 6
    fun whenText() {
        var textWhen = when (we) {
            0 -> "0"
            in 1..5 -> "1-5"
            in 6..10 -> "6-10"
            else -> "default"
        }
        kotlinTv.text = textWhen
    }

    /**
     * for：循环
     */
    var arrayList = listOf<String>("1", "2", "3", "4")

    fun forText() {
        for (e in arrayList) {
            toast(e)
        }

    }

    /**
     * is:相当于java中的instanceOf,
     * 判断是否是某个实例
     */
    fun isText() {
        if (d is Int) toast("is") else toast("no")

    }

    /**
     * null值检查
     */
    /*声明可空变量*/
    var d: Int? = 2

    /*函数返回可空*/
    fun parseInt(a: Int): Int? {
        return null;
    }

    /*调用时自动检查null，可设置默认值*/
    val files = File("Text").listFiles()

    fun textNull() {
//        kotlinTv.text = files?.size.toString()
//        kotlinTv.text=files?.size.toString()?:"empty"
        kotlinTv.text = d?.toString() ?: "empty"
    }

    /*如果不为null执行某操作*/
    fun textNull2() {
        d?.let {
            kotlinTv.text = d.toString()
        }
    }

    /**
     * if语句
     */
    fun ifText(a: Int, b: Int) = if (a > b) a else b

    fun ifText2(params: Int): String {
        val result = if (params == 1) {
            "one"
        } else if (params == 2) {
            "two"
        } else {
            "other"
        }
        return result
    }

    /**
     * 定义变量
     */
    var a: Int = 1//普通变量
    val b: Int = 1//只读变量，相当于final
    var c = 1//可推导出Int类型
    /**
     * 定义函数
     * Unit相当于java中的void
     * 可以省略
     */
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    /**
     * 点击事件
     */
    private fun click() {
        kotlinTv.text = "name:" + person.name + "\nage:" + person.age + "\nsex:" + person.sex;
        toast("toast ")
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
    private fun initByAuto() {
        kotlinTv = find(R.id.kotlin_tv)
        kotlinTv.text = "this is kotlin"
        kotlinTv.textColor = Color.parseColor("#aa00aa")
    }

}
