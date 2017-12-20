package com.raise.test

import com.raise.test.GtiServer.retrieveTime
import com.raise.test.GtiServer.saveTime
import jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.File
import java.io.Reader
import java.text.SimpleDateFormat
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus

val name = "raise";

fun main(args: Array<String>) {

    saveTime(System.currentTimeMillis())

    val retrieveTime = GtiServer.retrieveTime()

    val format = SimpleDateFormat("yyyy MM dd").format(retrieveTime)
    println(format)

    println(retrieveTime)
}

fun test_dd_file(){
    val file: File = File("D:/dd.json")
    val ddContent = file.inputStream().reader().useLines {
        var buffer: String = ""
        for (line in it) {
            buffer += line
        }
        buffer
    }

    val ddModel = DDParse.getVersion("v1_171101", ddContent)
    println(ddModel)
}