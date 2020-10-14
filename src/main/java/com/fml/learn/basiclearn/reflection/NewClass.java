package com.fml.learn.basiclearn.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NewClass {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> cClass = Class.forName("com.fml.learn.basiclearn.reflection.Student");


        Student student = (Student) cClass.newInstance();// 调用无参构造方法
        System.out.println(student);

        // 通过构造器创建对象
        cClass.getDeclaredConstructor();

        //  通过反射获取一个方法
        Method setName = cClass.getMethod("setName", String.class);
        // 激活方法
        setName.invoke(student, "ZMY");
        System.out.println(student.getName());

        // 获取属性
        Field name = cClass.getDeclaredField("name");

        // 私有属性需要关掉安全检测
        name.setAccessible(true);

        name.set(student, "zmy");




    }
}
