package com.raiseyang.plugins.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class SayHelloRobotKt : DefaultTask() {
    @Input
    var msg: String? = null
    @TaskAction
    fun start() {
        System.out.println("SayHelloRobotJava::MSG=$msg")
    }
}