package com.raise.test.composition

import com.raise.test.composition.inter.IComposite

/**
 * 小写字母开头的行被合并到上一行
 * 一行中如果末尾不是符号，则将下一行合并到该行
 */
class ConcatLineComposite : IComposite {
    override fun compose(text: List<String>): List<String> {
        // 反向扫描
        var tempStr = ""
        val resultList1 = mutableListOf<String>()
        text.reversed().forEachIndexed { index, line ->
            var newLine = line
            // 上一次有小写开头的句子
            if (tempStr.isNotEmpty()) {
                newLine = "$newLine $tempStr"
                tempStr = ""
            }

            if (newLine.first() in 'a'..'z') {
                // 小写开头，等待下一行合并
                tempStr = newLine
            } else {
                // 加到结果里面
                resultList1.add(newLine)
            }
        }
        tempStr = ""
        val resultList2 = mutableListOf<String>()
        // resultList1是反序的
        resultList1.forEachIndexed { index, line ->
            var newLine = line
            println("newLine=[$newLine]")
            // 上一次有
            if (tempStr.isNotEmpty()) {
                newLine = "$newLine $tempStr"
                tempStr = ""
            }

            if (index + 1 < resultList1.size && resultList1[index + 1].last() !in listOf('.', '\'', '!')) {
                // 小写开头，等待下一行合并;  缓存当前行
                tempStr = newLine
                println("tempStr=[$tempStr]")
            } else {
                // 加到结果里面
                resultList2.add(newLine)
            }


//            if (index == 0) {
//                resultList2.add(line)
//                return@forEachIndexed // 相当于continue
//            }
//            if () {
//                // 上一行句子没有结束,把当前行数据连接在末尾
//                tempStr2 = line
//                resultList2.add(line + " " + resultList1[index + 1])
//                jump = true
//            } else {
//                jump = false
//            }

        }



        return resultList2.reversed()
    }
}