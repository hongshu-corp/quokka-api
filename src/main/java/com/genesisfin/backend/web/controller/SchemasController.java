package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.schemas.FormFieldType;
import com.genesisfin.backend.web.schemas.PresentationSchema;
import com.genesisfin.backend.web.schemas.json.Form;
import com.genesisfin.backend.web.schemas.json.Model;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schemas")
public class SchemasController {

//    @Autowired
//    ConversionService conversionService;

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
        String packageName = "com.genesisfin.backend.web";

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new TypeAnnotationsScanner(),
                        new SubTypesScanner()));

        Class<?> findClass = null;
        for (Class<?> entityClass : reflections.getTypesAnnotatedWith(PresentationSchema.class)) {
            PresentationSchema annotation = entityClass.getAnnotation(PresentationSchema.class);
            if (annotation.name().equalsIgnoreCase(id)) {
                findClass = entityClass;
                break;
            }
        }

        if (findClass == null) {
            return null;
        }

        Model model = new Model();
        model.setName(id);


        return model;
    }
}
