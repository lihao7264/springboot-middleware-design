package com.atlihao.springboot.schedule.annotation;

import com.atlihao.springboot.schedule.DoJoinPoint;
import com.atlihao.springboot.schedule.config.DcsSchedulingConfiguration;
import com.atlihao.springboot.schedule.task.CronTaskRegister;
import com.atlihao.springboot.schedule.task.SchedulingConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({DcsSchedulingConfiguration.class})
@ImportAutoConfiguration({SchedulingConfig.class, CronTaskRegister.class, DoJoinPoint.class})
@ComponentScan("com.atlihao.springboot.*")
public @interface EnableDcsScheduling {
}
