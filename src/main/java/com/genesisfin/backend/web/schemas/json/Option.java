package com.genesisfin.backend.web.schemas.json;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Option {
    private String label;

    private String value;
}
