package com.genesisfin.backend.web.model.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.genesisfin.backend.web.model.ModelBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListIdToEntityDeserializer extends JsonDeserializer<Collection<?>> implements ContextualDeserializer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private JavaType valueType;

    public ListIdToEntityDeserializer() {
    }

    public ListIdToEntityDeserializer(JavaType valueType) {
        this.valueType = valueType;
    }


    @Override
    public Collection<?> deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {

        Collection<?> collection = new ArrayList<>();
        ArrayList array = (ArrayList)ctxt.readValue(parser, ArrayList.class);

        TypeFactory typeFactory = ctxt.getTypeFactory();
        CollectionType itemsListType = typeFactory.constructCollectionType(List.class, valueType);


        ArrayList<ModelBase> result = new ArrayList<>();

        array.forEach(id -> {
            ModelBase item = null;
            try {
                item = (ModelBase)valueType.getRawClass().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            item.setId(OBJECT_MAPPER.convertValue(id, Long.TYPE));
            result.add(item);
        });
        return OBJECT_MAPPER.convertValue(result, itemsListType);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctx, BeanProperty property) throws JsonMappingException {
        JavaType resultType = property.getType();
        JavaType valueType = resultType.containedType(0);

        return new ListIdToEntityDeserializer(valueType);
    }
}

