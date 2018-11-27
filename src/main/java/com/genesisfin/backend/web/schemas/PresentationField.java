package com.genesisfin.backend.web.schemas;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface PresentationField {
    // Java does not support use Object here.
    String defaultValue() default "";

    PropertyType type() default PropertyType.Text;

    PresentationOption[] options() default {};
}
