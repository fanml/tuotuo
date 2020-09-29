package com.fml.learn.basiclearn.patterns.singleton;

/**
 * 懒汉式单例
 */
public class LazySingleton {
    // 如果编写的是多线程程序，则不要删除上例代码中的关键字 volatile 和 synchronized，否则将存在线程非安全的问题。
    // 如果不删除这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
    private LazySingleton() {
        System.out.println(Thread.currentThread().getName());
    }

    private static volatile LazySingleton lazySingleton = null;

    public static synchronized LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        } else {
            System.out.println("lazySingleton在此之前已经new了");
        }
        return lazySingleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazySingleton.getInstance();
            }).start();
        }

    }
}
