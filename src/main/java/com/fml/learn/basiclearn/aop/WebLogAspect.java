package com.fml.learn.basiclearn.aop;

import com.fml.learn.basiclearn.syslog.OperateLog;
import com.fml.learn.basiclearn.syslog.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class WebLogAspect {

    private final String POINT_CUT = "@annotation(com.fml.learn.basiclearn.syslog.SystemLog)";

    @Pointcut(POINT_CUT)
    public void webLog() {

    }

    @Around("webLog()")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=============");
        Map<String, Object> map = getAnnotationValue(joinPoint);
        OperateLog operateLog = new OperateLog();
        operateLog.setModule((String) map.get("module"));
        operateLog.setOpType((String) map.get("opType"));
        // 模拟存入数据库
        log.info("operateLog: " + operateLog.toString());
        return joinPoint.proceed();
    }

    public static Map<String, Object> getAnnotationValue(JoinPoint joinPoint) throws Exception {
        // 通过切面获取类路径名
        String targetName = joinPoint.getTarget().getClass().getName();
        log.info("targetName : " + targetName);
        // 通过切面获取方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("methodName : " + methodName);
        // 通过切面获取参数
        Object[] arguments = joinPoint.getArgs();
        log.info("arguments : " + arguments.length);

        // 反射获取该类
        Class<?> targetClass = Class.forName(targetName);
        // 反射获取方法名数组
        Method[] methods = targetClass.getMethods();
        Map<String, Object> map = new HashMap<>();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (arguments.length == parameterTypes.length) {
                    String description = method.getAnnotation(SystemLog.class).description();
                    String module = method.getAnnotation(SystemLog.class).module().name();
                    String opType = method.getAnnotation(SystemLog.class).opType().name();
                    String primaryKeyName = method.getAnnotation(SystemLog.class).primaryKeyName();
                    int primaryKeySort = method.getAnnotation(SystemLog.class).primaryKeySort();
                    Class<?> clazz = method.getAnnotation(SystemLog.class).primaryKeyBelongClass();
                    map.put("module", module);
                    map.put("opType", opType);
                    map.put("business", description);
                    map.put("primaryKeyName", primaryKeyName);
                    map.put("primaryKeySort", primaryKeySort);
                    map.put("primaryKeyBelongClass", clazz);
                    break;

                }

            }
        }
        return map;
    }

}
