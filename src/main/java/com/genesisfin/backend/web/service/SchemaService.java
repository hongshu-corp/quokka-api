package com.genesisfin.backend.web.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.genesisfin.backend.web.schemas.*;
import com.genesisfin.backend.web.schemas.definitions.*;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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

    public ModelDefinition build(Class<?> aClass) {
        ModelDefinition definition = new ModelDefinition();

        PresentationSchema annotation = aClass.getAnnotation(PresentationSchema.class);
        definition.setName(annotation.name());
        definition.setProperties(buildFields(aClass));

        return definition;
    }

    public HashMap<String, FieldDefinition> buildFields(Class<?> aClass) {
        HashMap<String, FieldDefinition> map = new HashMap<>();
        for (Field field : aClass.getDeclaredFields()) {
            HashMap<String, FieldDefinition> kv = buildField(field);
            map.putAll(kv);
        }

        return map;
    }

    public HashMap<String, FieldDefinition> buildField(Field field) {
        HashMap<String, FieldDefinition> map = new HashMap<>();
        PresentationField pf = field.getAnnotation(PresentationField.class);
        if (pf == null) return map;

        FieldDefinition prop = new FieldDefinition();
        map.put(getFieldKey(field, pf), prop);
        prop.setDefaultValue(buildFieldDefinitionDefaultValue(field, pf));
        prop.setType(buildFieldDefinitionFieldType(pf));
        prop.setOptions(buildDefinitionOptions(pf.options()));

        prop.setForm(buildFormFieldDefinition(pf, field.getAnnotation(PresentationFormField.class)));
        prop.setColumn(buildColumnDefinition(pf, field.getAnnotation(PresentationColumn.class)));
        prop.setDetail(buildDetailDefinition(pf, field.getAnnotation(PresentationDetailField.class)));
        prop.setSearchable(buildSearchableDefinition(pf, field.getAnnotation(PresentationSearchField.class)));

        return map;
    }

    public Object buildFieldDefinitionDefaultValue(Field field, PresentationField pf) {
        if (pf.defaultValue().isEmpty()) {
            return null;
        }

        return conversionService.convert(pf.defaultValue(), field.getType());
    }

    public FieldType buildFieldDefinitionFieldType(PresentationField pf) {
        FieldType type = pf.type();
        if (type == FieldType.None) {
            return null;
        }

        return type;
    }

    public FormFieldDefinition buildFormFieldDefinition(PresentationField pf, PresentationFormField formField) {
        if (pf == null || formField == null) {
            return null;
        }

        FormFieldDefinition definition = new FormFieldDefinition();
        // type
        if (formField.type() != FormFieldType.None) {
            definition.setType(formField.type());
        }
        // non-arrays
        copyNonDefaultValue(formField, definition, PresentationFormField.class);

        definition.setOptions(buildDefinitionOptions(formField.options()));
        definition.setRules(buildFormFiledDefinitionRules(formField));

        return definition;
    }

    private <A extends Annotation> void copyNonDefaultValue(Object presentationField, Object definition,
                                                            Class<A> annotationClass) {
        List<String> skipFields = Arrays.asList("type");
        List<Field> fields = Arrays.stream(definition.getClass().getDeclaredFields())
                .filter(x -> !skipFields.contains(x.getName()) && x.isAnnotationPresent(JsonProperty.class) && !x.getType().getName().equals("java.util.List"))
                .collect(Collectors.toList());

        for (Field field : fields) {
            BeanWrapperImpl setter = new BeanWrapperImpl(definition);

            Method getMethod = null;
            try {
                getMethod = annotationClass.getDeclaredMethod(field.getName());

                Object defaultValue = getMethod.getDefaultValue();
                Object value = getMethod.invoke(presentationField);

                if (value.equals(defaultValue)) {
                    continue;
                }

                setter.setPropertyValue(field.getName(), conversionService.convert(value, field.getType()));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            }

            if (getMethod == null) {
                continue;
            }
        }
    }

    public List<OptionDefinition> buildDefinitionOptions(PresentationOption[] source) {
        List<OptionDefinition> options = new ArrayList<>();
        for (PresentationOption po : source) {
            OptionDefinition option = new OptionDefinition().setLabel(po.label()).setValue(po.value());
            options.add(option);
        }
        return options;
    }

    private List<RuleDefinition> buildFormFiledDefinitionRules(PresentationFormField pf) {
        List<RuleDefinition> rules = new ArrayList<>();
        for (PresentationRule r : pf.rules()) {
            RuleDefinition rule = new RuleDefinition()
                    .setMessage(r.message())
                    .setRequired(Optional.of(r.required()))
                    .setTrigger(r.trigger());

            rules.add(rule);
        }

        return rules;
    }

    private String getFieldKey(Field field, PresentationField pf) {
        if (pf.name().isEmpty()) {
            return field.getName();
        }

        return pf.name();
    }

    public ColumnDefinition buildColumnDefinition(PresentationField pf, PresentationColumn column) {
        if (pf == null || column == null) {
            return null;
        }

        ColumnDefinition definition = new ColumnDefinition();
        // type
        if (column.type() != ColumnType.None) {
            definition.setType(column.type());
        }
        // non-arrays
        copyNonDefaultValue(column, definition, PresentationColumn.class);

        definition.setOptions(buildDefinitionOptions(column.options()));

        return definition;
    }

    public DetailFieldDefinition buildDetailDefinition(PresentationField pf, PresentationDetailField detailField) {
        if (pf == null || detailField == null) {
            return null;
        }
        DetailFieldDefinition definition = new DetailFieldDefinition();
        return definition;
    }

    public SearchFieldDefinition buildSearchableDefinition(PresentationField pf, PresentationSearchField searchField) {
        if (pf == null || searchField == null) {
            return null;
        }
        SearchFieldDefinition definition = new SearchFieldDefinition();
        return definition;
    }
}
