package com.genesisfin.backend.web.schemas.json;

import com.genesisfin.backend.web.schemas.FormFieldType;
import com.genesisfin.backend.web.schemas.definitions.FormFieldDefinition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

class FormFieldDefinitionTest {

    @Nested
    class SerializationTest extends JacksonTestBase {
        @Test
        void optional_value_test() {
            FormFieldDefinition formFieldDefinition = new FormFieldDefinition();
            formFieldDefinition.setMin(Optional.of(3));
            formFieldDefinition.setType(FormFieldType.Checkbox);

            String result = serialize(formFieldDefinition);

            assertEquals("{\"type\":\"checkbox\",\"min\":3}", result);
        }

    }
}
