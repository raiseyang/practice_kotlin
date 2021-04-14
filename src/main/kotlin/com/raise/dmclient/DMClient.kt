package com.raise.dmclient

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

fun main() {
    val resp1 = DMClient.reqPackage1()
    println("resp1=$resp1")
}

object DMClient {

    var okHttpClient: OkHttpClient = OkHttpClient.Builder().build()


    fun reqPackage1(): String? {

        val request: Request = Request.Builder()
            .url("http://127.0.0.1:8080/funambol/dm")
            .headers(DMHelper.getHeaders())
            .post(DMHelper.getPkg(1).toRequestBody())
            .build()

        okHttpClient.newCall(request).execute().use { response -> return response.body?.string() }
    }

    fun parseResp1() {
        //<RespURI>http://127.0.0.1:8080/funambol/dm?sid=W0JAMTY2YjE1ZjEtMTYxNzY5NDk3OTYyNA</RespURI>
        //<NextNonce xmlns='syncml:metinf'>IidsI0p+cTtcYy1RdCB3Mg==</NextNonce>
    }


}