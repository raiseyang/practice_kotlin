package com.raise.test.composition

import com.raise.test.composition.inter.IComposite

/**
 * 91打字网排版限制：一行不能超过90字符
 */
class NinetyLimitComposite : IComposite {

    private val limitLength = 90

    override fun compose(text: List<String>): List<String> = text.flatMap { line ->
        if (line.length > limitLength) {
            // 有超过限制

            // 寻找90行往前的[. ]index
            val lastIndexOf = line.substring(0, limitLength).lastIndexOf(". ")
            if (lastIndexOf == -1) {
                // 没有找到， 找空格
                line.substring(0, limitLength).lastIndexOf(' ').let {
                    val firstLine = line.substring(0, it + 1)
                    val secondLine = line.substring(it + 1)
                    return@flatMap compose(listOf(firstLine, secondLine))
                }
            } else {
                // 找到了:通过. 分割;   3：不包括当前字符的长度1，加上[. ]自己的长度2
                val firstLine = line.substring(0, lastIndexOf + 2)
                val secondLine = line.substring(lastIndexOf + 2)
                return@flatMap compose(listOf(firstLine, secondLine))
            }
        } else {
            // 没有超过限制，直接返回
            listOf(line)
        }
    }

}