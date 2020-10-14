package com.fml.learn.basiclearn.reflection;

import com.fml.learn.basiclearn.annotation.MetaAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ClassInfo {
    public static void main(String[] args) throws NoSuchFieldException {
        Student student = new Student();
        Class c = student.getClass();
        System.out.println("类名+包名" + c.getName());
        System.out.println("类名" + c.getSimpleName());

        Field[] publicFields = c.getFields(); // 获得类public属性
        for (Field f : publicFields) {
            System.out.println("publicFields " + f);
        }


        Field[] declaredFields = c.getDeclaredFields(); //获得类所有属性
        for (Field f : declaredFields) {
            System.out.println("declaredFields " + f);
        }
        // 获得指定属性
        Field name = c.getDeclaredField("age");
        // 获取类注解
        Annotation[] annotations = c.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a);
        }
        // 获取类指定注解以及值
        MetaAnnotation annotation = (MetaAnnotation) c.getAnnotation(MetaAnnotation.class);
        System.out.println(annotation.name());

        // 获取属性上的注解

        Field eye = c.getDeclaredField("eye");
        MetaAnnotation annotation1 = eye.getAnnotation(MetaAnnotation.class);
        System.out.println(annotation1.name() + annotation1.age());

    }
}
