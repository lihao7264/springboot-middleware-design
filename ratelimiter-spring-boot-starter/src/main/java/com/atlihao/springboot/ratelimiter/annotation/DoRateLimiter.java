package com.atlihao.springboot.ratelimiter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lihao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoRateLimiter {

    /**
     * 限流许可量
     *
     * @return
     */
    double permitsPerSecond() default 0D;

    /**
     * 失败结果 JSON
     *
     * @return
     */
    String returnJson() default "";

}
