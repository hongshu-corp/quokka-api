package com.genesisfin.backend.web.service;

import com.genesisfin.backend.web.schemas.PresentationSchema;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SchemaService {
    private ConversionService conversionService;

    public SchemaService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public Set<Class<?>> getEntityClasses() {
        return getEntityClasses("com.genesisfin.backend.web");
    }

    public Set<Class<?>> getEntityClasses(String packageName) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new TypeAnnotationsScanner(),
                        new SubTypesScanner()));
        return reflections.getTypesAnnotatedWith(PresentationSchema.class);
    }

    public Class<?> getEntityClass(String id, String packageName) {
        Set<Class<?>> entityClasses = getEntityClasses(packageName);

        Class<?> findClass = null;
        for (Class<?> entityClass : entityClasses) {
            PresentationSchema annotation = entityClass.getAnnotation(PresentationSchema.class);
            if (annotation.name().equalsIgnoreCase(id)) {
                findClass = entityClass;
                break;
            }
        }
        return findClass;
    }

    public Class<?> getEntityClass(String id) {
        return getEntityClass(id, "com.genesisfin.backend.web");
    }
}
