package com.raiseyang.plugins.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public class SayHelloRobotJava extends DefaultTask {

    @Input
    public String msg;

    @TaskAction
    public void start(){
        System.out.println("SayHelloRobotJava::MSG="+msg);
    }

}
