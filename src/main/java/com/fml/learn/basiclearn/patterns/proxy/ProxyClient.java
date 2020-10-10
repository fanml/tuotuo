package com.fml.learn.basiclearn.patterns.proxy;

public class ProxyClient {
    public static void main(String[] args) {
        SubjectProxy subjectProxy = new SubjectProxy();
        subjectProxy.Request();
    }
}
