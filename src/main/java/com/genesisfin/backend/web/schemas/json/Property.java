package com.genesisfin.backend.web.schemas.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.genesisfin.backend.web.schemas.PropertyType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Property {
    @JsonProperty("default")
    private Object defaultValue;

    @JsonProperty
    private PropertyType type;

    @JsonProperty
    private Column column;

    @JsonProperty
    private Form form;

    @JsonProperty
    private List<Option> options;

}
