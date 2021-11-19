package com.raise.test

import com.raise.test.composition.*
import java.io.File
import java.io.FileOutputStream

// 源文件目录，需要修改
const val srcTextFile = "F:/temp/a.txt"

fun main(args: Array<String>) {
    startTextComposition(srcTextFile)
}

fun startTextComposition(filePath: String) {

    val destFile = getDestFile(filePath)
    if (destFile.exists()) destFile.delete()
    val fos = FileOutputStream(destFile, true)

    val readText = File(filePath).readText(charset = Charsets.UTF_8)
    print("readText=[$readText]\n")
    //[\n]=0x0a LF   [\r]=0x0d CR
    val text = readText.split(Regex("[\r\n]"))

    val composition = with(Composition()) {
        addComposite(EmptyLineComposite(DELETE))
        addComposite(PunctuationComposite())
        addComposite(ConcatLineComposite())
        addComposite(BlankComposite())
        addComposite(NinetyLimitComposite())
//        addComposite(EmptyLineComposite(ADD))
        this
    }

    val result = composition.compose(text)
    result.forEach {
        // 写文件
        fos.write(it.toByteArray())
        fos.write("\n".toByteArray())
    }
}

private fun getDestFile(srcFile: String): File {
    return File(File(srcFile).parentFile, File(srcFile).name.replace(".txt", "") + "_composition.txt")
}
