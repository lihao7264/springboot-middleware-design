package com.atlihao.springboot.whitelist.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: lihao726726
 * @CreateDate: 2023/7/11 11:02 上午
 * @UpdateUser: lihao726726
 * @UpdateDate: 2023/7/11 11:02 上午
 * @Version: 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoWhiteList {

    String key() default "";

    String returnJson() default "";

}

