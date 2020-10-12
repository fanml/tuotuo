package com.fml.learn.basiclearn.reflection;

public class GetReflection {
    // 反射获取类的5种方式
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();

        Class<? extends Person> aClass = person.getClass();
        System.out.println(aClass.hashCode());

        Class<Student> bClass = Student.class;
        System.out.println(bClass.hashCode());

        Class<?> cClass = Class.forName("com.fml.learn.basiclearn.reflection.Student");
        System.out.println(cClass.hashCode());

        // 基本内置类型的包装类都有一个type属性
        Class<Integer> type = Integer.TYPE;
        System.out.println(type);

        // 获取父类的class
        Class<?> superclass = aClass.getSuperclass();
        System.out.println(superclass.hashCode());


    }
}

class Person {
    String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {
    public Student() {
        this.name = "连长";
    }
}
