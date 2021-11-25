package com.raise.test

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main(args: Array<String>) {
    println("hello world:args.size=${args.count()}")

    println("food = $dinnerFood")
    dinnerFood = "peach"
    println("food = $dinnerFood")

}

/**
 * by lazy 用于val定义的变量，延迟初始化
 *
 * 因为lazy只实现了变量个get委托，所以不能用在var变量上
 */
val todayFood by lazy { "banana" }

/**
 * by Delegates可用于var定义的变量，在变量get、set的时候做一些校验
 * Delegates.notNull()返回一个[ReadWriteProperty]实现了get和set;
 */
var tomorrowFood by Delegates.notNull<String>()

//自己实现[ReadWriteProperty]写一个属性委托
class FoodDelegate : ReadWriteProperty<Any?, String> {
    var value = "pear"
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("change the food from ${this.value} to $value")
        this.value = value
    }

}

// 使用委托初始化对象
var dinnerFood by FoodDelegate()


//自己实现一个类委托
interface IFruit {
    fun isRed()
}

object Orange : IFruit {
    override fun isRed() {
        println("not red")
    }
}

//Orange是一个具体的对象，如果Orange不是Object，而是class就不行
//天然的支持委托，不需要通过装饰模式来持有Orange对象，再重写接口方法做委托
class SouthOrange : IFruit by Orange

// 普通写法
class NorthOrange : IFruit {

    val orange = Orange

    override fun isRed() {
        orange.isRed()
    }
}