package com.fml.learn.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Spring中使用@Async注解使Even监听事件之间的执行变为异步
 */
@Slf4j
@Component
public class DemoEventListener implements ApplicationListener<DemoEvent> {
    @Override
    @Async
    public void onApplicationEvent(DemoEvent event) {
        String msg = event.getMsg();
        log.info("DemoEventListener,监听到了 DemoEvent 发布的消息:" + msg);
        //让线程睡眠是用来测试,监听者监听到发布者发布事件后,执行任务的时候
        //是否是同步执行
        if (msg.equals("测试消息监听")) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
