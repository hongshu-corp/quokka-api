package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.schemas.json.Model;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class SchemasControllerTest {

    private SchemasController controller = new SchemasController();

    @Nested
    class TestShow {
        @Test
        void should_find_the_model() {
            Model model = controller.show("user");

            assertEquals("user", model.getName());
        }

    }
}
