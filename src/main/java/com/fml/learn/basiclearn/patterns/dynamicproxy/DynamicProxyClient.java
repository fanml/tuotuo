package com.fml.learn.basiclearn.patterns.dynamicproxy;

public class DynamicProxyClient {
    public static void main(String[] args) {
        // 真实主题
        UserTestImpl userTest = new UserTestImpl();
        // 生成动态代理
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler();
        dynamicProxyHandler.setTarget(userTest);
        UserTest proxy = (UserTest) dynamicProxyHandler.getProxy();
        proxy.add();
        proxy.delete();
    }
}
