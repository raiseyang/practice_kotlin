package com.raise.test

/**
 * 包含标点符号
 * 返回标点符号的index
 */
fun String.containPunctuation(): Int {
    // 匹配字符
    val matchResult = Regex("[^a-zA-Z0-9]").find(this)
    return matchResult?.range?.first ?: -1
}

fun main() {
    val a = "22.t35xf2"
    val joinToString = a.split("${a[2]}").joinToString(separator = " ${a[2]} ") { it }
    println(joinToString)
}