package http

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import com.sun.corba.se.spi.presentation.rmi.StubAdapter.request
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {

    var Etag: String? = ""
    val modifyTime: String?

    val client: OkHttpClient = OkHttpClient()
/*

    val request = Request.Builder()
            .url("http://otlgsmu4m.bkt.clouddn.com/log_print.png")
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.95 Safari/537.36")
            .build()

    val response = client.newCall(request).execute()
    val headers = response.headers()
    println(response.code())
    headers.names().forEach {
        if (it == "Etag") {
            Etag = headers[it]
            println("$it:${headers[it]}")
        }
    }

    println()
    println("-------------------")
    Thread.sleep(TimeUnit.SECONDS.toMillis(1))
*/

//    val head = headers.newBuilder()
//            .add("If-Modified-Since", "Sun, 30 Jul 2017 06:19:50 GMT")
//            .add("If-None-Match", Etag)
//            .build()

    val formatTime = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).format(Date(119,6,30,6,19,50))
    println(formatTime)
    println("Sun, 30 Jul 2017 06:19:50 GMT")

    val time = formatTime.replace("CST","GMT")
    println(time)
    val request1 = Request.Builder()
            .url("http://otlgsmu4m.bkt.clouddn.com/log_print.png")
//            .header("If-Modified-Since", "Sun, 30 Jul 2017 06:19:50 GMT")
            .header("If-Modified-Since", time)
//            .header("If-None-Match", "FvyaHQgLS79woEpKfjEu3-rT1znC")
            .build()
//
    val response1 = client.newCall(request1).execute()
    val headers1 = response1.headers()

    println(response1.code())
    headers1.names().forEach {
//        println("$it:${headers1[it]}")
    }


}