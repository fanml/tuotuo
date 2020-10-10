package com.fml.learn.basiclearn.patterns.proxy;

/**
 * 真实主题
 */
public class RealSubject implements Subject {
    @Override
    public void Request() {
        System.out.println("RealSubject 内容");
    }
}
