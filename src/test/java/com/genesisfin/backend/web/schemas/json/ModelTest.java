package com.genesisfin.backend.web.schemas.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ModelTest {
    @Nested
    public class SerializationTest extends JacksonTestBase {
        @Test
        public void serialization() throws JsonProcessingException {
            Model model = new Model();
            model.setName("user");
            model.setProperties(new HashMap<>());

            String result = serialize(model);

            assertEquals("{\"name\":\"user\",\"props\":{}}", result);
        }

    }
}
