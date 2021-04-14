package com.raise.dmclient

import sun.misc.BASE64Encoder
import java.security.MessageDigest
import kotlin.experimental.and
import kotlin.random.Random

/**
 * The digest supplied in the Cred element is computed as follows:
 * Let H = the MD5 Hashing function.
 * Let Digest = the output of the MD5 Hashing function.
 * Let B64 = the base64 encoding function.
 * Digest = H(B64(H(username:password)):nonce)
 */
object DMCredentialUtil {
    /**
     * 计算<Cred>标签内认证字符串
     * Cred>
     *    <Meta>
     *        <Format xmlns='syncml:metinf'>b64</Format>
     *        <Type xmlns='syncml:metinf'>syncml:auth-md5</Type>
     *    </Meta>
     *     <Data>4y2fmC9XY0xpphx2LoG8QQ==</Data>
     * </Cred>
     *
     * username:password=Bruce1:OhBehave
     * nonce=nonce
     * H(username:password)=8b37a2dc6eb36baad50d180ef1a417c9
     * B64(H(username:password))=izei3G6za6rVDRgO8aQXyQ==
     * Digest=96764b6f7c0186fe60bc296df02fa6ef
     * cred=lnZLb3wBhv5gvClt8C+m7w==
     * @param nonce 可选
     */
    fun calcCred(user: String, pwd: String, nonce: String = ""): String {
        println("user=$user,pwd=$pwd,nonce=$nonce")
        var nonce2 = nonce
        if (nonce.isEmpty()) {
            nonce2 = Random.nextInt().toString()
        }
        val up = "$user:$pwd"
        println("username:password=$up")
        val md5A = md5Encode(up.toByteArray())
        println("H(username:password)=${byteArrayInHex(md5A)}")
        val b644 = encodeB64(md5A)
        println("B64(H(username:password))=$b644")
        val digest = md5Encode("$b644:$nonce2".toByteArray())
        println("Digest=${byteArrayInHex(digest)}")
        val cred = encodeB64(digest)
        println("cred=$cred")
        return cred
    }

    /**
     * 计算md5值
     */
    private fun md5Encode(text: ByteArray): ByteArray {
        val digest = MessageDigest.getInstance("md5")
        val buffer = digest.digest(text)
        val sb = StringBuffer()
        for (b in buffer) {
            val a: Byte = b and 0xff.toByte()
            var hex = Integer.toHexString(a.toInt())
            if (hex.length == 1) {
                hex = "0$hex"
            }
            sb.append(hex)
        }
        return buffer
    }

    private fun encodeB64(byteArray: ByteArray): String {
        return BASE64Encoder().encode(byteArray)
    }

    private fun byteArrayInHex(byteArray: ByteArray): String {
        val sb = StringBuffer()
        for (b in byteArray) {
            val a: Byte = b.and(0xff.toByte())
            var hex = a.toUByte().toString(16).replace("-", "0")
            sb.append(hex)
        }
        return sb.toString()
    }
}