package com.genesisfin.backend.web.schemas.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;

@Setter
@Getter
@Accessors(chain = true)
public class ModelDefinition {

    private String name;

    private HashMap<String, FieldDefinition> Properties;

    @JsonProperty("props")
    public HashMap<String, FieldDefinition> getProperties() {
        return Properties;
    }
}
