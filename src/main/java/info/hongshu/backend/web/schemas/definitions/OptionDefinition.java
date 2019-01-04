package info.hongshu.backend.web.schemas.definitions;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OptionDefinition {
    private String label;

    private String value;
}
