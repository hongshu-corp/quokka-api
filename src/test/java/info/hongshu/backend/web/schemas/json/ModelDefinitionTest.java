package info.hongshu.backend.web.schemas.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.hongshu.backend.web.schemas.definitions.ModelDefinition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ModelDefinitionTest {
    @Nested
    public class SerializationTest extends JacksonTestBase {
        @Test
        public void serialization() throws JsonProcessingException {
            ModelDefinition modelDefinition = new ModelDefinition();
            modelDefinition.setName("user");
            modelDefinition.setProperties(new HashMap<>());

            String result = serialize(modelDefinition);

            assertEquals("{\"name\":\"user\",\"props\":{}}", result);
        }

    }
}
