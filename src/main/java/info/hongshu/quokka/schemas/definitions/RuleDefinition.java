package info.hongshu.quokka.schemas.definitions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Accessors(chain = true)
public class RuleDefinition {
    @JsonProperty
    private Optional<Boolean> required;

    @JsonProperty
    private String message;

    @JsonProperty
    private String trigger;
}
