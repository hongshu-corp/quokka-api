package info.hongshu.quokka.schemas.definitions;

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
