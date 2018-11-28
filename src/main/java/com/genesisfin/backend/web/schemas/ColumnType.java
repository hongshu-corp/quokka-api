package com.genesisfin.backend.web.schemas;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ColumnType {
    None("none"), // If it is none, use the parent type.
    Text("text"),
    Password("password"),
    Map("map"),
    LinkShow("linkShow"),
    LinkUpdate("linkUpdate"),
    Datetime("datetime");

    @JsonValue
    private String type;

    ColumnType(String value) {
        this.type = value;
    }
}
