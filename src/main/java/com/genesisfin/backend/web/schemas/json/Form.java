package com.genesisfin.backend.web.schemas.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.genesisfin.backend.web.schemas.FormType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

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
    private Optional<Integer> precision;

    @JsonProperty
    private Optional<Integer> min;

    @JsonProperty
    private Optional<Integer> max;

    @JsonProperty
    private Optional<Integer> step;

    @JsonProperty
    private Optional<Boolean> disabled;

    @JsonProperty
    private String inactiveText;

    @JsonProperty
    private String activeText;

    @JsonProperty
    private Object activeValue;

    @JsonProperty
    private String inactiveValue;

    @JsonProperty
    private List<Option> options;

    @JsonProperty
    private List<Rule> rules;
}
