package com.raise.test.composition

import com.raise.test.composition.inter.IComposite

/**
 * 修整符号间的空格问题
 *
//1。逗号和前引号之间要有空格
//2。is简写成‘时，前后不需要由空格
//3。[out'The]这种情况，需要先在标点符号旁边都加上空格
 */
class BlankComposite : IComposite {

    // 简写 he's   I'm   can't   we' ve   wasn't   o 'clock  They're
    private val abbrList = listOf("s", "m", "ve", "t", "clock", "re")

    override fun compose(text: List<String>): List<String> {
        return text.map {
            composeOneLine(it)
        }
    }

    private fun composeOneLine(line: String): String {
        var bufferLine = line

        // 需要在标点符号后面加上空格
        // step1:先在标点符号上加上空格
        val punctuation = listOf(',', '.', '!', '?', '\'')
        val step1 = bufferLine.map {
            if (it in punctuation) {
                return@map String(charArrayOf(' ', it, ' '))
            } else {
                return@map String(charArrayOf(it))
            }
        }.joinToString(separator = "") { it }

        var step2: String = ""
        var unquote = true
        val workList = step1.split(Regex("[ ]+")).filter { it.isNotBlank() }
        workList
            .forEachIndexed { index, word ->
                if (word.last() !in punctuation) {
                    // 普通单词
                    step2 += word
                    if (index < workList.size - 1 &&
                        workList[index + 1].first() in punctuation
                    ) {
                        // 下一个单词的开头是标点符号;;
                        // 下一个单词是前引号，并且不是简写
                        if (unquote && workList[index + 1].first() == '\''
                            && (index < workList.size - 2 && workList[index + 2] !in abbrList)
                        ) {
                            step2 += " "
                        }
                    } else {
                        step2 += " "
                    }
                } else {
                    //有标点符号
                    step2 += word
                    val punc = word.last()
                    if ('\'' == punc) {
                        // 判断是否是简写
                        if (index < workList.size - 1 &&
                            (workList[index + 1] in abbrList)
                        ) {
                            // 是简写，不加空格
                            return@forEachIndexed
                        }

                        unquote = !unquote
                        if (!unquote) {
                            // 前引号
                        } else {
                            // 后引号
                            step2 += " "
                        }
                    } else {
                        // 其他标点符号
                        if (index < workList.size - 1 &&
                            workList[index + 1].first() in punctuation
                        ) {
                            // 两个标点符号连在一起
                            if (workList[index + 1].first() == '\'' && unquote) {
                                // 如果引号已关闭；；
                                step2 += " "
                            }
                        } else {
                            step2 += " "
                        }
                    }
                }
            }
        step2 = step2.trim()
//        println()
        println("step2=[$step2]")
        return step2
    }

}