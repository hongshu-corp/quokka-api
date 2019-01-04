package info.hongshu.backend.web.schemas.definitions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.hongshu.backend.web.schemas.ColumnType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Accessors(chain = true)
public class ColumnDefinition {
    @JsonProperty
    private ColumnType type;

    @JsonProperty
    private String width;

    @JsonProperty
    private String minWidth;

    @JsonProperty
    private String maxWidth;

    @JsonProperty
    private List<OptionDefinition> options;

}
