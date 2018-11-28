package com.genesisfin.backend.web.schemas;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface PresentationColumn {
    ColumnType type() default ColumnType.None;

    String width() default "";

    String minWidth() default "";

    String maxWidth() default "";

    PresentationOption[] options() default {};
}
