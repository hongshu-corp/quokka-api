package com.genesisfin.backend.web.service;

import com.genesisfin.backend.web.schemas.PresentationSchema;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.junit.Assert.assertNotNull;

public class SchemaServiceTest {
    ConversionService conversionService = new DefaultConversionService();
    private SchemaService service = new SchemaService(conversionService);

    @Nested
    public class getEntityClassTest {

        @Test
        void should_get_entity() {
            Class<?> aClass = service.getEntityClass("myentity", "com.genesisfin.backend.web.service");

            assertNotNull(aClass);
        }

        @PresentationSchema(name = "myentity")
        public class MyEntity {

        }
    }

}
