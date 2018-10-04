package com.xgq.annotation;

import java.lang.annotation.*;


/**
   * @author HeJD
   * @date 2018/9/18 14:05
   * @Description:系统日志注解
   */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
