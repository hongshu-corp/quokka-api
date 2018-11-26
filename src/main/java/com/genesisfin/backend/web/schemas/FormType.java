package com.genesisfin.backend.web.schemas;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FormType {
    Text("txt"),
    Number("number"),
    Password("password"),
    Email("email"),
    Tel("tel"),
    Url("url"),
    TextArea("textarea"),
    Map("map"),
    Radio("radio"),
    Checkbox("checkbox"),
    Rate("rate"),
    Select("select"),
    Slider("slider"),
    Switch("switch"),
    Date("date"),
    Time("time"),
    Datetime("datetime");

    @JsonValue
    private String type;

    private FormType(String value) {
        this.type = value;
    }
}
