package com.raiseyang.plugins.tasks


import org.gradle.api.Plugin
import org.gradle.api.Project

class SayHelloPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "project name = $project.name"
        project.task("SayHelloPlugin", type: SayHelloRobot) {
            msg = "SayHelloPlugin's msg"
        }
    }
}
