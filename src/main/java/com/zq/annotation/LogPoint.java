package com.zq.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LogPoint {
    String log() default "";
}
