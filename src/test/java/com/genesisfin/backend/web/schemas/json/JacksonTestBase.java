package com.genesisfin.backend.web.schemas.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTestBase {
    ObjectMapper objectMapper;

    public JacksonTestBase() {
        objectMapper = new ObjectMapper();
    }

    public String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
