package com.example.pantaijava.com.getcapacitor.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CapacitorPlugin {
    String name() default "";

    com.getcapacitor.annotation.Permission permissions() default;

    int[] requestCodes() default {};
}
