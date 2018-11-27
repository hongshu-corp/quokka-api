package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.schemas.FormFieldType;
import com.genesisfin.backend.web.schemas.json.Form;
import com.genesisfin.backend.web.schemas.json.Model;
import com.genesisfin.backend.web.service.SchemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schemas")
public class SchemasController {

    private SchemaService schemaService;
    public SchemasController(SchemaService schemaService) {
        this.schemaService = schemaService;
    }

    @GetMapping("ping")
    public Form ping() {
        Form form = new Form();
        form.setMin(Optional.of(3));
        form.setType(FormFieldType.Checkbox);

        return form;
    }

    @GetMapping
    public List<Model> index() {
        return null;
    }

    @GetMapping(path = "/{id}")
    public Model show(@PathVariable String id) {
        Class<?> findClass = schemaService.getEntityClass(id);

        if (findClass == null) {
            return null;
        }

        Model model = new Model();
        model.setName(id);


        return model;
    }

}
