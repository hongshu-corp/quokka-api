package com.genesisfin.backend.web.schemas;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention( RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface PresentationFormField {
    FormFieldType type() default FormFieldType.None;

    String placeholder() default "";

    int precision() default 0;

    int min() default 0;

    int max() default 0;

    int step() default 0;

    boolean disabled() default false;

    String inactiveText() default "";

    String activeText() default "";

    String activeValue() default "";

    String inactiveValue() default "";

    String dataUrl() default "";

    // Get data method
    String method() default "get";

    PresentationOption[] options() default {};

    PresentationRule[] rules() default {};
}
