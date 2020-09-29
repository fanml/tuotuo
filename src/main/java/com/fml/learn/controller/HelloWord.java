package com.fml.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {
    @GetMapping("/hello")
    public String Hello(){
        return "2020-09-29 begin study";
    }
}
