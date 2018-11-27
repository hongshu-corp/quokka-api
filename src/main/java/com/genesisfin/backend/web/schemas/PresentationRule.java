package com.genesisfin.backend.web.schemas;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface PresentationRule {
    boolean required() default false;

    String message() default "";

    String trigger() default "blur";
}
