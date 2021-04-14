package com.raise.dmclient

import okhttp3.Headers
import java.io.FileReader

fun main() {
    println("pkg1=${DMHelper.getPkg(1)}")
}

object DMHelper {

    //   'Cache-Control': 'private',
    //    'Connection': 'close',
    //    'User-Agent': 'HTTP SyncML Client [en] (WinNT; I)',
    //    'Accept-Language': 'en',
    //    'Accept-Charset': 'utf-8',
    //    # 'Host': "127.0.0.1\:9081",
    //    'Content-Type': 'application/vnd.syncml.dm+xml',
    //    # 'Content-Type': 'application/vnd.syncml.dm+wbxml',
    fun getHeaders() = Headers.Builder().let {
        it.add("'Cache-Control'", "private")
        it.add("Connection", "close")
        it.add("'User-Agent'", "HTTP SyncML Client [en] (WinNT; I)")
        it.add("Accept-Language", "en")
        it.add("Accept-Charset", "utf-8")
        it.add("Host", "127.0.0.1:8080")
        it.add("Content-Type", "application/vnd.syncml.dm+xml")
        it.add("Content-Type", "application/vnd.syncml.dm+wbxml")
            .build()
    }

    fun getPkg(num: Int): String {
        val fileName = "pkg$num.txt"
        println("getPkg = $fileName")
        return FileReader("${getResourcePath()}/pkg/$fileName").readText()
    }

    private fun getResourcePath(): String = "G:\\java-workspaces\\practice_kotlin\\src\\main\\resources"
}