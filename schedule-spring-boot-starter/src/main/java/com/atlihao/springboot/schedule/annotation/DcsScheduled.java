package com.atlihao.springboot.schedule.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lihao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DcsScheduled {

    String desc() default "缺省";

    String cron() default "";

    boolean autoStartup() default true;

}
