package com.raise.test

import com.raise.dmclient.DMCredentialUtil
import sun.misc.BASE64Decoder


fun main(args: Array<String>) {
    println("hello world")

    println(
        "calcCred() result = " +
                DMCredentialUtil.calcCred(
                    "funambol", "funambol",
                    String(BASE64Decoder().decodeBuffer("ZlQ5KCtNIGszQiljQyY0ZA=="))
                )
    )

}

