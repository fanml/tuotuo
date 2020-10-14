package com.fml.learn.basiclearn.syslog;

import java.lang.annotation.*;

@Target(value = {ElementType.METHOD, ElementType.TYPE_PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SystemLog {
    /**
     * 操作描述 业务名称business
     *
     * @return
     */
    String description() default "";

    /**
     * 操作模块
     *
     * @return
     */
    OperateModule module();

    /**
     * 操作类型 create modify delete
     *
     * @return
     */
    OperateType opType();

    /**
     * 主键入参参数名称，入参中的哪个参数为主键
     *
     * @return
     */
    String primaryKeyName() default "";

    /**
     * 主键在参数中的顺序，从0开始，默认0
     */
    int primaryKeySort() default 0;

    /**
     * 主键所属类
     *
     * @return
     */
    Class<?> primaryKeyBelongClass();
}
