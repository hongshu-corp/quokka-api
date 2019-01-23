package info.hongshu.quokka.schemas;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface PresentationField {
    // Used as the hash map key.
    // If the name is null or empty, use the field name.
    String name() default "";

    // Java does not support use Object here.
    String defaultValue() default "";

    FieldType type() default FieldType.Text;

    PresentationOption[] options() default {};

    int order() default 0;
}
