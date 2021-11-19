package com.raise.test.composition.inter

/**
 * 定义一个排版接口
 * 可以实现一个具体的排版；注意每个具体的实现类都不依赖其他实现类；
 */
interface IComposite {
    /**
     * @param text 文本的数据，每个元素代表一行
     */
    fun compose(text: List<String>): List<String>

}