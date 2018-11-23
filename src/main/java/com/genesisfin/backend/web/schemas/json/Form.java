package com.genesisfin.backend.web.schemas.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.genesisfin.backend.web.schemas.FormType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Accessors(chain = true)
public class Form {
    @JsonProperty
    private FormType type;

    @JsonProperty
    private String placeholder;

    @JsonProperty
    private List<Option> options;

    @JsonProperty
    private List<Rule> rules;
}
