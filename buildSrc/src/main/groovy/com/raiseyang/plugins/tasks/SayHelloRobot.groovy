package com.raiseyang.plugins.tasks

import okhttp3.OkHttpClient
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class SayHelloRobot extends DefaultTask {

    @Input
    String msg

    SayHelloRobot(){
        description "i'm robot"
    }

    @TaskAction
    void start() {
        def okhttp = new OkHttpClient()
        println "SayHelloRobot:start() msg=$msg"
    }
}
