package com.genesisfin.backend.web.schemas.json;

import com.genesisfin.backend.web.schemas.FormFieldType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

class FormTest {

    @Nested
    class SerializationTest extends JacksonTestBase {
        @Test
        void optional_value_test() {
            Form form = new Form();
            form.setMin(Optional.of(3));
            form.setType(FormFieldType.Checkbox);

            String result = serialize(form);

            assertEquals("{\"type\":\"checkbox\",\"min\":3}", result);
        }

    }
}
