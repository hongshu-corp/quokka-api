package com.genesisfin.backend.web.schemas.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;

@Setter
@Getter
@Accessors(chain = true)
public class Model {

    private String name;

    private HashMap<String, Property> Properties;

    @JsonProperty("props")
    public HashMap<String, Property> getProperties() {
        return Properties;
    }
}
