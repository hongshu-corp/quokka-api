package com.genesisfin.backend.web.schemas.json;

import com.genesisfin.backend.web.schemas.FieldType;
import com.genesisfin.backend.web.schemas.definitions.FieldDefinition;
import com.genesisfin.backend.web.schemas.definitions.OptionDefinition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PropertyTest {
    @Nested
    public class SerializationTest extends JacksonTestBase {
        @Test
        public void should_use_the_real_text_for_type() {
            FieldDefinition fieldDefinition = new FieldDefinition();
            fieldDefinition.setType(FieldType.Text).setDefaultValue(1);

            String result = serialize(fieldDefinition);

            assertEquals("{\"type\":\"txt\",\"default\":1}", result);
        }

        @Test
        public void options() {
            FieldDefinition fieldDefinition = new FieldDefinition();
            fieldDefinition.setOptions(Arrays.asList(new OptionDefinition().setLabel("label").setValue("value")));

            assertEquals("{\"options\":[{\"label\":\"label\",\"value\":\"value\"}]}", serialize(fieldDefinition));
        }

    }
}
