package com.raise.test.composition

import com.raise.test.composition.inter.IComposite

val ADD = true
val DELETE = false

/**
 * 1.增加空白行
 * 2。过滤空白行
 * @param mode [ADD] or [DELETE]
 */
class EmptyLineComposite(private val mode: Boolean, private val addNum: Int = 1) : IComposite {
    override fun compose(text: List<String>): List<String> {
        return when (mode) {
            ADD -> {
                text.flatMap {
                    val mutableList = mutableListOf<String>()
                    mutableList.add(it)
                    repeat(addNum) {
                        mutableList.add(" ")
                    }
                    mutableList.toList()
                }
            }
            DELETE -> {
                text.filter { it.isNotBlank() }
            }
            else -> throw IllegalStateException("EmptyLineComposite when mode else ->")
        }
    }
}