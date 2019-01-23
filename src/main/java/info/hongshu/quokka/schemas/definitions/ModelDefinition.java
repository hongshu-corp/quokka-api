package info.hongshu.quokka.schemas.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

@Setter
@Getter
@Accessors(chain = true)
public class ModelDefinition {

    private String name;

    private Map<String, FieldDefinition> Properties;

    @JsonProperty("props")
    public Map<String, FieldDefinition> getProperties() {
        return Properties;
    }
}
