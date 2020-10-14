package com.fml.learn.basiclearn.annotation;

import java.lang.annotation.*;

/**
 * 元注解学习
 */
// 表示注解可以使用在哪些地方
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})

// 表示我们的注解在什么地方有效  字节码:class  > 运行时:runtime > source
@Retention(value = RetentionPolicy.RUNTIME)

//表示是否将注解生成到javadoc中
@Documented
// 表示子类是否可以继承父类的该注解
@Inherited
public @interface MetaAnnotation {
    // 注解的参数：参数类型 +参数名() default 默认值；
    String name();

    int age() default 0;

    // 默认值-1表示不存在
    int id() default -1;

    String[] schools() default {"辽工大", "清华"};

    // value可以省略
    String value() default "";
}
