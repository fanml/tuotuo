package com.fml.learn.basiclearn.patterns.singleton;

/**
 * 静态内部类单例实现
 */
public class InnerClassSingleton {
    private InnerClassSingleton() {

    }

    public static InnerClassSingleton getInstance() {
        return MInnerClassSingleton.innerClassSingleton;
    }

    private static class MInnerClassSingleton {
        private static final InnerClassSingleton innerClassSingleton = new InnerClassSingleton();
    }

}
