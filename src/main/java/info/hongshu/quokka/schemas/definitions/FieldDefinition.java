package info.hongshu.quokka.schemas.definitions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.hongshu.quokka.schemas.FieldType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldDefinition {
    @JsonProperty("default")
    private Object defaultValue;

    @JsonProperty
    private FieldType type;

    @JsonProperty
    private ColumnDefinition column;

    @JsonProperty
    private FormFieldDefinition form;

    @JsonProperty
    private DetailFieldDefinition detail;

    @JsonProperty
    private SearchFieldDefinition searchable;

    @JsonProperty
    private List<OptionDefinition> options;

}
