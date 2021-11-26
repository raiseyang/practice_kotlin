package com.raise.test

import com.raise.test.composition.*
import com.raise.test.composition.inter.IComposite
import java.io.File
import java.io.FileOutputStream

// 源文件目录，需要修改
const val srcTextFile = "F:/temp/a.txt"

const val error_text = """
请输出正确的命令，示例：
java -jar text_composition_xx.jar -x F:/temp/a.txt
java -jar text_composition_xx.jar -n F:/temp/a.txt

查看帮助：
-h
"""

const val help_text = """
-x     : for dzxjr.com
-n     : for NCE Lesson

运行后，会在源文件同目录下生成目标文件
"""

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println(error_text)
        return
    }

    when (args[0]) {
        "-h" -> {
            println(help_text)
        }
        "-x" -> {
            startTextComposition(args[1], getDZXJRComposite())
        }
        "-n" -> {
            startTextComposition(args[1], getNCEComposite())
        }
        else -> {
            println(error_text)
        }
    }
}

/**
 * 开始排版
 */
fun startTextComposition(filePath: String, composition: IComposite) {

    val destFile = getDestFile(filePath)
    if (destFile.exists()) destFile.delete()
    val fos = FileOutputStream(destFile, true)

    val readText = File(filePath).readText(charset = Charsets.UTF_8)
    print("readText=[$readText]\n")
    //[\n]=0x0a LF   [\r]=0x0d CR
    val text = readText.split(Regex("[\r\n]"))

    val result = composition.compose(text)
    result.forEach {
        // 写文件
        fos.write(it.toByteArray())
        fos.write("\n".toByteArray())
    }

    print(
        """
        
        Congratulations! 
        The destination file path is `${destFile.absolutePath}`.
        Have a good time!
    """.trimIndent()
    )
}

/**
 * 获取NCE的文章排版
 */
private fun getNCEComposite() = with(Composition()) {
    addComposite(EmptyLineComposite(DELETE))
    addComposite(PunctuationComposite())
    addComposite(ConcatLineComposite())
    addComposite(BlankComposite())
    addComposite(EmptyLineComposite(ADD))
    this
}

/**
 * 获取打字网站的文章排版
 */
private fun getDZXJRComposite() = with(Composition()) {
    addComposite(EmptyLineComposite(DELETE))
    addComposite(PunctuationComposite())
    addComposite(ConcatLineComposite())
    addComposite(BlankComposite())
    addComposite(NinetyLimitComposite())
    this
}

private fun getDestFile(srcFile: String): File {

    print("==1:"+File(srcFile).parentFile)
    print("==2:"+File(srcFile).name.replace(".txt", "") + "_composition.txt")

    return File(File(srcFile).parentFile, File(srcFile).name.replace(".txt", "") + "_composition.txt")
}
