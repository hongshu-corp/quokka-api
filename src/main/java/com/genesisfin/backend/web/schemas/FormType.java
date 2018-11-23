package com.genesisfin.backend.web.schemas;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FormType {
    Text("txt"),
    Password("password"),
    Map("map"),
    Datetime("datetime");

    @JsonValue
    private String type;

    private FormType(String value) {
        this.type = value;
    }
}
