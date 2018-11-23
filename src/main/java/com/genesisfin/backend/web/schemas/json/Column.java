package com.genesisfin.backend.web.schemas.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.genesisfin.backend.web.schemas.ColumnType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Accessors(chain = true)
public class Column {
    @JsonProperty
    private ColumnType type;

    @JsonProperty
    private String width;

    @JsonProperty
    private List<Option> options;

}
