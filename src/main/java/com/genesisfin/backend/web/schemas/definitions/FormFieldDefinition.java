package com.genesisfin.backend.web.schemas.definitions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.genesisfin.backend.web.schemas.FormFieldType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Accessors(chain = true)
public class FormFieldDefinition {
    @JsonProperty
    private FormFieldType type;

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
    private String activeValue;

    @JsonProperty
    private String inactiveValue;

    @JsonProperty
    private List<OptionDefinition> options;

    @JsonProperty
    private List<RuleDefinition> rules;
}
