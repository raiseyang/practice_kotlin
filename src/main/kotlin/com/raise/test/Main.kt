package com.raise.test

import com.raise.wbxml.Roundtrip
import org.kxml2.io.KXmlParser
import org.kxml2.io.KXmlSerializer
import org.xmlpull.v1.XmlPullParser
import java.io.ByteArrayOutputStream
import java.io.StringReader


fun main(args: Array<String>) {
    println("hello world")

//    println(
//        "calcCred() result = " +
//                DMCredentialUtil.calcCred(
//                    "funambol", "funambol",
//                    String(BASE64Decoder().decodeBuffer("ZlQ5KCtNIGszQiljQyY0ZA=="))
//                )
//    )
//    parseXML()
//    parseWBXML()

}

fun parseWBXML() {
    // 输入xml格式
    val xpp = KXmlParser()
    xpp.setInput(
        StringReader(
            """
        <syncml><Source><LocURI>IMEI:861139020041601</LocURI><LocName>funambol</LocName></Source></syncml>
    """.trimIndent()
        )
    )
    // 输出wbxml
    val baos = ByteArrayOutputStream()
    val xs = KXmlSerializer()
    xs.setOutput(baos, null)

    Roundtrip(xpp, xs).roundTrip()

    val wbxml: ByteArray = baos.toByteArray()

    println("********* WBXML size: " + wbxml.size + " ***********")

    var i = 0
    while (i < wbxml.size) {
        for (j in i until Math.min(i + 16, wbxml.size)) {
            val b = wbxml[j].toInt() and 0x0ff
            print(Integer.toHexString(b / 16))
            print(Integer.toHexString(b % 16))
            print(' ')
        }
        for (j in i until Math.min(i + 16, wbxml.size)) {
            val b = wbxml[j].toInt()
            print(if (b >= 32 && b <= 127) b.toChar() else '?')
        }
        println()
        i += 16
    }
}

fun parseXML() {
    val xpp = KXmlParser()
    xpp.setInput(
        StringReader(
            """
        <syncml><Source><LocURI>IMEI:861139020041601</LocURI><LocName>funambol</LocName></Source></syncml>
    """.trimIndent()
        )
    )
    var eventType = xpp.eventType
    while (true) {
        if (eventType == XmlPullParser.START_DOCUMENT) {
            println("Start document")
        } else if (eventType == XmlPullParser.END_DOCUMENT) {
            println("End document")
            break
        } else if (eventType == XmlPullParser.START_TAG) {
            if ("LocURI" == xpp.name) {
                println("Start tag " + xpp.name + ",text = " + xpp.nextText())
            } else {
                println("Start tag " + xpp.name)
            }
        } else if (eventType == XmlPullParser.END_TAG) {
            println("End tag " + xpp.name)
        } else if (eventType == XmlPullParser.TEXT) {
            println("Text " + xpp.text)
        }
        eventType = xpp.next() //需要手动调用，获取下一个事件类型
    }
}
