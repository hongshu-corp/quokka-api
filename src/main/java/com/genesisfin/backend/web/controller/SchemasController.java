package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.schemas.FormType;
import com.genesisfin.backend.web.schemas.json.Form;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/schemas")
public class SchemasController {
    @GetMapping("ping")
    public Form ping() {
        Form form = new Form();
        form.setMin(Optional.of(3));
        form.setType(FormType.Checkbox);

        return form;
    }
}
