package com.fml.learn.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan("com.fml.learn.event")
@EnableAsync
public class EventConfig {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        //用注入的形式完成事件发布
        DemoPubLisher pushListener = context.getBean(DemoPubLisher.class);
        pushListener.pushListener("测试消息监听");
        pushListener.pushListener("测试消息监听1");

        context.close();
    }
}
