package com.fml.learn.controller;

import com.fml.learn.basiclearn.syslog.OperateModule;
import com.fml.learn.basiclearn.syslog.OperateType;
import com.fml.learn.basiclearn.syslog.SystemLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {
    @GetMapping("/hello")
    public String Hello() {
        return "2020-09-29 begin study";
    }

    @GetMapping("/systemLogTest")
    @SystemLog(module = OperateModule.SPRINGBOOT, opType = OperateType.QUERY, primaryKeyBelongClass = HelloWord.class, description = "测试注解systemLog")
    public String systemLogTest() {
        return "systemLogTest";
    }

}
