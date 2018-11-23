package com.genesisfin.backend.web.schemas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PropertyType {
    Text("txt"),
    Password("password"),
    Map("map"),
    Datetime("datetime");

    @JsonValue
    private String type;

    private PropertyType(String value) {
        this.type = value;
    }
}
