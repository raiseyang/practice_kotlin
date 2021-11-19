package com.raise.test.composition

import com.raise.test.composition.inter.IComposite

/**
 * 排版器
 */
class Composition : IComposite {

    private val compositeList: MutableList<IComposite> = mutableListOf()

    /**
     * 增加排版插件
     */
    fun addComposite(composite: IComposite) {
        compositeList.add(composite)
    }

    /**
     * 开始排版
     * 将按照插入的排版插件顺序，开始排版
     */
    override fun compose(text: List<String>): List<String> {
        var result = text
        compositeList.forEach {
            result = it.compose(result)
        }
        return result
    }
}