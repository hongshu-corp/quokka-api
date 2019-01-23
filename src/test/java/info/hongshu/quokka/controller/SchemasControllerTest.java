package info.hongshu.quokka.controller;

import info.hongshu.quokka.service.SchemaService;
import org.springframework.core.convert.support.DefaultConversionService;

class SchemasControllerTest {

    private SchemaService service = new SchemaService(new DefaultConversionService());
    private SchemasController controller = new SchemasController(service);

}
