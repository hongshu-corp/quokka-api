package info.hongshu.quokka.controller;

import info.hongshu.quokka.service.SchemaService;
import info.hongshu.quokka.schemas.FormFieldType;
import info.hongshu.quokka.schemas.definitions.FormFieldDefinition;
import info.hongshu.quokka.schemas.definitions.ModelDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import static info.hongshu.quokka.util.Strings.pluralize;

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
    public HashMap<String, ModelDefinition> index() {
        HashMap<String, ModelDefinition> map = new HashMap<>();
        Set<Class<?>> classes = schemaService.getEntityClasses();
        for (Class<?> klass: classes) {
            ModelDefinition definition = schemaService.build(klass);
            map.put(pluralize(definition.getName()), definition);
        }
        return map;
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
