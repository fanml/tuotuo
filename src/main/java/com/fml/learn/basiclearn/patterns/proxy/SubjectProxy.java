package com.fml.learn.basiclearn.patterns.proxy;

/**
 * 代理
 */
public class SubjectProxy implements Subject {

    private RealSubject realSubject = new RealSubject();

    @Override
    public void Request() {
        preRequest();
        realSubject.Request();
        postRequest();
    }

    public void preRequest() {
        System.out.println("preRequest");
    }

    public void postRequest() {
        System.out.println("postRequest");
    }

}
