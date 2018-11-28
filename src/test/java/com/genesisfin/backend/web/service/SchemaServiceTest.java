package com.genesisfin.backend.web.service;

import com.genesisfin.backend.web.schemas.*;
import com.genesisfin.backend.web.schemas.definitions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class SchemaServiceTest {
    ConversionService conversionService = new DefaultConversionService();
    private SchemaService service = new SchemaService(conversionService);

    @Nested
    public class GetEntityClassTest {

        @Test
        void should_get_entity() {
            Class<?> aClass = service.getEntityClass("myentity", "com.genesisfin.backend.web.service");

            assertNotNull(aClass);
        }

        @PresentationSchema(name = "myentity")
        public class MyEntity {
            @PresentationField
            private String name;
        }
    }

    @Nested
    public class BuildSimpleTest{
        @Test
        void test_assignment(){
            Class<?> aClass = service.getEntityClass("myentity", "com.genesisfin.backend.web.service");
            ModelDefinition definition = service.build(aClass);
            assertEquals("myentity", definition.getName());
            assertEquals(1, definition.getProperties().size());
        }
    }


    @Nested
    public class BuildSimpleModelDefinitionTest {
        @Test
        void build_fields_simple_test() {
            HashMap<String, FieldDefinition> map = service.buildFields(TestEntity.class);

            assertEquals(2, map.size());
            assertTrue(map.containsKey("name"));
            assertTrue(map.containsKey("e"));
        }

        @Test
        void default_value_when_empty_string() throws NoSuchFieldException {
            Field field = TestEntity.class.getDeclaredField("name");
            PresentationField annotation = field.getAnnotation(PresentationField.class);

            // the default value String is "" => Object should be null
            Object defaultValue = service.buildFieldDefinitionDefaultValue(field, annotation);
            assertNull(defaultValue);
        }

        @Test
        void default_value_conversion_when_string_to_boolean() throws NoSuchFieldException {
            Field field = BuildSimpleModelDefinitionTest.class.getDeclaredField("nonEmptyBooleanField");
            Object defaultValue = service.buildFieldDefinitionDefaultValue(field,
                    field.getAnnotation(PresentationField.class));
            assertTrue(defaultValue.equals(false));
        }

        @Test
        void default_value_conversion_when_string_to_long() throws NoSuchFieldException {
            Field field = BuildSimpleModelDefinitionTest.class.getDeclaredField("nonEmptyLongField");
            Object defaultValue = service.buildFieldDefinitionDefaultValue(field,
                    field.getAnnotation(PresentationField.class));
            assertTrue(defaultValue.equals(10000l));
        }

        @PresentationField(defaultValue = "false")
        private boolean nonEmptyBooleanField;
        @PresentationField(defaultValue = "10000")
        private Long nonEmptyLongField;

        @PresentationSchema(name = "test")
        public class TestEntity {
            @PresentationField
            private String name;

            @PresentationField(name = "e")
            private String email;
        }
    }

    @Nested
    public class BuildPropertyFieldTypeTest {
        @Test
        void when_none_type_get_null() throws NoSuchFieldException {
            FieldType fieldType =
                    service.buildFieldDefinitionFieldType(BuildPropertyFieldTypeTest.class.getDeclaredField("name").getAnnotation(PresentationField.class));

            assertNull(fieldType);
        }

        void when_common_type_get_cast() throws NoSuchFieldException {
            FieldType fieldType =
                    service.buildFieldDefinitionFieldType(BuildPropertyFieldTypeTest.class.getDeclaredField("email").getAnnotation(PresentationField.class));

            assertEquals(FieldType.Map, fieldType);
        }

        @PresentationField(type = FieldType.None)
        private String name;
        @PresentationField(type = FieldType.Map)
        private String email;

    }

    @Nested
    public class BuildFieldDefinitionOptionTest {
        @Test
        void convert_options() throws NoSuchFieldException {
            List<OptionDefinition> options =
                    service.buildDefinitionOptions(BuildFieldDefinitionOptionTest.class.getDeclaredField("name").getAnnotation(PresentationField.class).options());

            assertEquals(2, options.size());
            assertEquals("msn", options.get(0).getLabel());
            assertEquals("msn.com", options.get(0).getValue());
        }

        @PresentationField(options = {
                @PresentationOption(label = "msn", value = "msn.com"),
                @PresentationOption(label = "gmail", value = "gmail.com")
        })
        private String name;
    }

    @Nested
    public class BuildFormFieldDefinitionTest {
        @Test
        void should_be_null_if_field_type_is_none() throws NoSuchFieldException {
            FormFieldDefinition definition = getFormFieldDefinition("name");
            assertNull(definition.getType());
        }

        @Test
        void should_be_select_if_field_type_is_select() throws NoSuchFieldException {
            FormFieldDefinition definition = getFormFieldDefinition("email");
            assertEquals(FormFieldType.Select, definition.getType());
        }

        @Test
        void should_copy_none_default_value_field_to_form_field_definition() throws NoSuchFieldException {
            FormFieldDefinition definition = getFormFieldDefinition("withDefaultValue");
            assertTrue(definition.getMax().get().compareTo(1000) == 0);
            assertEquals(null, definition.getMin());
        }

        @Test
        void should_copy_options() throws NoSuchFieldException {
            FormFieldDefinition definition = getFormFieldDefinition("withOptions");
            assertEquals("test", definition.getOptions().get(0).getLabel());
            assertEquals("1", definition.getOptions().get(0).getValue());
        }

        @Test
        void should_copy_rules() throws NoSuchFieldException {
            FormFieldDefinition definition = getFormFieldDefinition("withRules");
            assertEquals("Required", definition.getRules().get(0).getMessage());
            assertEquals("blur", definition.getRules().get(0).getTrigger());
            assertEquals(true, definition.getRules().get(0).getRequired().get());
        }

        @Test
        void empty_options_get_null() throws NoSuchFieldException {
            FormFieldDefinition definition = getFormFieldDefinition("withRules");
            assertNull(definition.getOptions());
        }

        @Test
        void empty_rules_get_null() throws NoSuchFieldException {
            FormFieldDefinition definition = getFormFieldDefinition("name");
            assertNull(definition.getRules());
        }

        private FormFieldDefinition getFormFieldDefinition(String fieldName) throws NoSuchFieldException {
            Field field = BuildFormFieldDefinitionTest.class.getDeclaredField(fieldName);
            PresentationField pf = field.getAnnotation(PresentationField.class);
            PresentationFormField formField = field.getAnnotation(PresentationFormField.class);

            return service.buildFormFieldDefinition(pf, formField);
        }

        @PresentationField(type = FieldType.Datetime)
        @PresentationFormField
        private String name;

        @PresentationField(type = FieldType.Datetime)
        @PresentationFormField(type = FormFieldType.Select)
        private String email;

        @PresentationField
        @PresentationFormField(type = FormFieldType.Checkbox, max = 1000)
        private String withDefaultValue;

        @PresentationField
        @PresentationFormField(type = FormFieldType.Select, options = {
                @PresentationOption(label = "test", value = "1")
        })
        private String withOptions;

        @PresentationField
        @PresentationFormField(rules = {
                @PresentationRule(message = "Required", trigger = "blur", required = true)
        })
        private String withRules;
    }

    @Nested
    public class BuildColumnDefinitionTest {
        @Test
        void should_be_null_if_field_type_is_none() throws NoSuchFieldException {
            ColumnDefinition definition = getColumnDefinition("name");
            assertNull(definition.getType());
        }

        @Test
        void should_be_copy_type_if_is_not_none() throws NoSuchFieldException {
            ColumnDefinition definition = getColumnDefinition("mapColumn");
            assertEquals(ColumnType.Map, definition.getType());
        }

        @Test
        void should_copy_properties() throws NoSuchFieldException {
            ColumnDefinition definition = getColumnDefinition("copyColumn");
            assertEquals("10px", definition.getWidth());
        }

        private ColumnDefinition getColumnDefinition(String fieldName) throws NoSuchFieldException {
            Field field = BuildColumnDefinitionTest.class.getDeclaredField(fieldName);
            PresentationField pf = field.getAnnotation(PresentationField.class);
            PresentationColumn column = field.getAnnotation(PresentationColumn.class);

            return service.buildColumnDefinition(pf, column);
        }

        @PresentationField(type = FieldType.Datetime)
        @PresentationColumn
        private String name;

        @PresentationField
        @PresentationColumn(type = ColumnType.Map)
        private String mapColumn;

        @PresentationField
        @PresentationColumn(type = ColumnType.Map, width = "10px", maxWidth = "100px")
        private String copyColumn;


    }
}
