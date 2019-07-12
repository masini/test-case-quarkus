package org.acme.quickstart.rest;

import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import java.util.Objects;

public class SimpleJsonGenerator {
    private final JsonGenerator jsonGenerator;
    private final SerializationContext ctx;

    SimpleJsonGenerator(JsonGenerator jsonGenerator, SerializationContext ctx) {
        this.jsonGenerator = jsonGenerator;
        this.ctx = ctx;
    }

    public static SimpleJsonGenerator simpleGenerator(JsonGenerator jsonGenerator, SerializationContext ctx) {
        return new SimpleJsonGenerator(jsonGenerator, ctx);
    }

    public SimpleJsonGenerator writeIfNotNull(String key, Object value) {

        Objects.requireNonNull(key, "key cannot be null");

        if (value != null) {
            ctx.serialize(key, value, jsonGenerator);
        }

        return this;
    }
}