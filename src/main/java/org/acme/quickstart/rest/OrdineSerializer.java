package org.acme.quickstart.rest;

import io.quarkus.arc.Unremovable;
import org.acme.quickstart.entities.Ordine;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

import static org.acme.quickstart.rest.SimpleJsonGenerator.simpleGenerator;

//TODO: verificare uso in PiattoOrdinato
@ApplicationScoped
@Unremovable
public class OrdineSerializer implements JsonbSerializer<Ordine> {

    @Inject
    GenericEntitySerializer genericEntitySerializer;

    @Override
    public void serialize(Ordine ordine, JsonGenerator jsonGenerator, SerializationContext ctx) {

        jsonGenerator.writeStartObject();

        genericEntitySerializer.serialize(ordine, jsonGenerator, ctx);

        simpleGenerator(jsonGenerator, ctx)
                .writeIfNotNull("id", ordine.getBarcode())
                .writeIfNotNull("stato", ordine.getStato())
                .writeIfNotNull("canale", ordine.getCanale())
                .writeIfNotNull("bar", ordine.getBar())
                .writeIfNotNull("numeroComanda", ordine.getNumeroComanda())
                .writeIfNotNull("asporto", ordine.isAsporto())
                .writeIfNotNull("prioritario", ordine.isPrioritario());

        jsonGenerator.writeEnd();

    }
}