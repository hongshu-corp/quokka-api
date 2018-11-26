package com.genesisfin.backend.web.schemas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PropertyType {
    Text("txt"),
    Number("number"),
    Email("email"),
    Url("url"),
    Map("map"),
    Select("select"),
    Slider("slider"),
    Date("date"),
    Time("time"),
    Datetime("datetime");

    @JsonValue
    private String type;

    private PropertyType(String value) {
        this.type = value;
    }
}
