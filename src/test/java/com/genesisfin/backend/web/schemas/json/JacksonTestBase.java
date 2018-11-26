package com.genesisfin.backend.web.schemas.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesisfin.backend.web.service.CustomObjectMapper;

public class JacksonTestBase {
    ObjectMapper objectMapper;

    public JacksonTestBase() {
        objectMapper = new CustomObjectMapper();
    }

    public String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
