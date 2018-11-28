package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.schemas.FormFieldType;
import com.genesisfin.backend.web.schemas.definitions.FormFieldDefinition;
import com.genesisfin.backend.web.schemas.definitions.ModelDefinition;
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
    public FormFieldDefinition ping() {
        FormFieldDefinition formFieldDefinition = new FormFieldDefinition();
        formFieldDefinition.setMin(Optional.of(3));
        formFieldDefinition.setType(FormFieldType.Checkbox);

        return formFieldDefinition;
    }

    @GetMapping
    public List<ModelDefinition> index() {
        return null;
    }

    @GetMapping(path = "/{id}")
    public ModelDefinition show(@PathVariable String id) {
        Class<?> findClass = schemaService.getEntityClass(id);

        if (findClass == null) {
            return null;
        }

        ModelDefinition definition = schemaService.build(findClass);

        return definition;
    }

}
