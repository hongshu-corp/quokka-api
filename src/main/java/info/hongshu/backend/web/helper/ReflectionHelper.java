package info.hongshu.backend.web.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.hongshu.backend.web.model.ModelBase;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;

public class ReflectionHelper {
    public static <T extends ModelBase> void copyProperties(T model, T original) {
        if (model == null) return;

        BeanWrapperImpl setter = new BeanWrapperImpl(original);
        BeanWrapperImpl getter = new BeanWrapperImpl(model);
        for (Field field : model.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonProperty.class)) {
                String name = field.getName();
                setter.setPropertyValue(name, getter.getPropertyValue(name));
            }
        }

    }
}
