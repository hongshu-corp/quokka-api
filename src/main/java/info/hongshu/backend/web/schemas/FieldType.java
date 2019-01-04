package info.hongshu.backend.web.schemas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FieldType {
    None("none"),
    Text("text"),
    Password("password"),
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

    FieldType(String value) {
        this.type = value;
    }
}
