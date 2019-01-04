package info.hongshu.backend.web.controller;

import info.hongshu.backend.web.service.SchemaService;
import org.springframework.core.convert.support.DefaultConversionService;

class SchemasControllerTest {

    private SchemaService service = new SchemaService(new DefaultConversionService());
    private SchemasController controller = new SchemasController(service);

}
