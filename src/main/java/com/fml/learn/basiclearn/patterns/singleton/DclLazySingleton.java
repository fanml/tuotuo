package com.fml.learn.basiclearn.patterns.singleton;

/**
 * Dcl懒汉式单例
 */
public class DclLazySingleton {

    private DclLazySingleton() {
        synchronized (DclLazySingleton.class) {
            if (dclLazySingleton != null) {
                throw new RuntimeException("不要用反射破坏单例模式");
            }
        }
    }

    private static volatile DclLazySingleton dclLazySingleton;

    public static DclLazySingleton getInstance() {
        if (dclLazySingleton == null) {
            synchronized (DclLazySingleton.class) {
                if (dclLazySingleton == null) {
                    dclLazySingleton = new DclLazySingleton();
                }
            }
        }
        return dclLazySingleton;
    }
    // 反射可以破解
}
