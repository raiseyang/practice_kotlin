package com.raise.test.composition

import com.raise.test.composition.inter.IComposite

/**
 * 1。去除特殊字符
 * 2。将全角符号转换成半角符号
 */
class PunctuationComposite : IComposite {
    override fun compose(text: List<String>): List<String> {
        return text.map {
            composeOneLine(it)
        }
    }

    private fun composeOneLine(line: String): String {
        var bufferLine = line
        // 全角转换成半角符号
        bufferLine = bufferLine.replace("，", ",")
        bufferLine = bufferLine.replace("．", ".")
        bufferLine = bufferLine.replace("！", "!")
        bufferLine = bufferLine.replace("？", "?")
        bufferLine = bufferLine.replace("—", "-")
        bufferLine = bufferLine.replace("：", ":")
        bufferLine = bufferLine.replace("－", "-")
        bufferLine = bufferLine.replace("；", ";")
        bufferLine = bufferLine.replace("…", "...")
        bufferLine = bufferLine.replace("“", "\'")
        bufferLine = bufferLine.replace("”", "\'")

        // 去除非英文常用字符
        bufferLine = bufferLine.map {
            if (it > '~') {
                println("注意:删除非ASCII码字符,char=$it,hex=0x${it.code.toString(16)}")
                return@map ""
            } else {
                String(charArrayOf(it))
            }
        }.joinToString(separator = "") { it }

//    bufferLine = bufferLine.replace("", "'")
//    bufferLine = bufferLine.replace("", "'")
        // 符号后面需要有空格(两个符号之间除外)
        return bufferLine
    }
}