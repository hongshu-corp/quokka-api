package com.genesisfin.backend.web.schemas.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.genesisfin.backend.web.schemas.FormType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class FormTest {

    @Nested
    public class SerializationTest extends JacksonTestBase {
        @Test
        public void optinal_value_test() throws JsonProcessingException {
            Form form = new Form();
            form.setMin(Optional.of(3));
            form.setType(FormType.Checkbox);

            String result = serialize(form);

            assertEquals("{\"type\":\"checkbox\",\"min\":3}", result);
        }

    }
}
