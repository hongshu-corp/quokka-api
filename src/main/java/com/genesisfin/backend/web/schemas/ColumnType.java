package com.genesisfin.backend.web.schemas;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ColumnType {
    Text("txt"),
    Password("password"),
    Map("map"),
    Datetime("datetime");

    @JsonValue
    private String type;

    private ColumnType(String value) {
        this.type = value;
    }
}
