package info.hongshu.quokka.schemas;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FormFieldType {
    None("none"),
    Text("text"),
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

    FormFieldType(String value) {
        this.type = value;
    }
}
