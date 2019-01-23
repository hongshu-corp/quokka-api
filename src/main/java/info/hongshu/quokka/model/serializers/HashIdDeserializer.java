package info.hongshu.quokka.model.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import info.hongshu.quokka.service.HashidService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class HashIdDeserializer extends JsonDeserializer<Long> {
    @Autowired
    private HashidService hashidService;

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String hashed = ctxt.readValue(p, String.class);

        return hashidService.decode(hashed);
    }
}
