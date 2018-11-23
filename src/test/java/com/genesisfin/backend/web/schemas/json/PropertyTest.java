package com.genesisfin.backend.web.schemas.json;

import com.genesisfin.backend.web.schemas.PropertyType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PropertyTest {
    @Nested
    public class SerializationTest extends JacksonTestBase {
        @Test
        public void should_use_the_real_text_for_type() {
            Property property = new Property();
            property.setType(PropertyType.Text).setDefaultValue(1);

            String result = serialize(property);

            assertEquals("{\"default\":1,\"type\":\"txt\"}", result);
        }

        @Test
        public void options() {
            Property property = new Property();
            property.setOptions(Arrays.asList(new Option().setLabel("label").setValue("value")));

            assertEquals("{\"options\":[{\"label\":\"label\",\"value\":\"value\"}]}", serialize(property));
        }

    }
}
