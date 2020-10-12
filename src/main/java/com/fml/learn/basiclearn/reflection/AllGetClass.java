package com.fml.learn.basiclearn.reflection;

import java.lang.annotation.ElementType;

/**
 * 什么类型可以获取到class
 */
public class AllGetClass {
    public static void main(String[] args) {
        Class<Object> objectClass = Object.class; // 类
        Class<Comparable> comparableClass = Comparable.class; // 接口
        Class<String[]> aClass = String[].class; // 数组
        Class<int[][]> aClass1 = int[][].class; // 二维数组
        Class<Override> overrideClass = Override.class; // 注解
        Class<ElementType> elementTypeClass = ElementType.class;// 枚举
        Class<Integer> integerClass = Integer.class;// 基本数据类型
        Class<Void> voidClass = void.class;// void
        Class<Class> classClass = Class.class;// class

        int[] a = new int[10];
        int[] b = new int[100];
        System.out.println(a.getClass().hashCode());
        System.out.println(b.getClass().hashCode());
    }
}
