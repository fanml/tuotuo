package com.fml.learn.basiclearn.patterns.prototype;

/**
 * 原型模式
 * 1. 实现Cloneable接口
 * 2. 重写拷贝方法clone()
 */
public class ZhuPrototype implements Cloneable {
    private String name;
    private String age;

    public ZhuPrototype() {

    }

    public ZhuPrototype(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
//        Object obj = super.clone();
        // 实现深克隆的方式之一：将对象属性也克隆
//        ZhuPrototype zhuPrototype = (ZhuPrototype) obj;
//        zhuPrototype.name = (String) this.name.clone();
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ZhuPrototype{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
