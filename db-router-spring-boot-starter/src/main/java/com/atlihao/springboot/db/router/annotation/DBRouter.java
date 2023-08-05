package com.atlihao.springboot.db.router.annotation;

import java.lang.annotation.*;


/**
 * @author lihao
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DBRouter {

    String key() default "";

}
