package org.acme.quickstart.rest;

import io.quarkus.arc.Unremovable;
import org.acme.quickstart.entities.Abbinamento;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;

import static org.acme.quickstart.rest.SimpleJsonGenerator.simpleGenerator;

@ApplicationScoped
@Unremovable
public class AbbinamentoSerializer implements JsonbSerializer<Abbinamento> {

    @Inject
    PiattoOrdinatoSerializer piattoOrdinatoSerializer;

    @Inject
    GenericEntitySerializer genericEntitySerializer;

    @Override
    public void serialize(Abbinamento abbinamento, javax.json.stream.JsonGenerator jsonGenerator, SerializationContext ctx) {
        jsonGenerator.writeStartObject();

        genericEntitySerializer.serialize(abbinamento, jsonGenerator, ctx);

        simpleGenerator(jsonGenerator, ctx)
                .writeIfNotNull("id", abbinamento.getId())
                .writeIfNotNull("idAbbinamentoOriginale", abbinamento.getIdAbbinamentoOriginale())
                .writeIfNotNull("stato", abbinamento.getStato())
                .writeIfNotNull("nome", abbinamento.getNome());
//
//        if (abbinamento.getPiattoOrdinato() != null) {
//            jsonGenerator.writeKey("piattoOrdinato");
//            piattoOrdinatoSerializer.serialize(abbinamento.getPiattoOrdinato(), jsonGenerator, ctx);
//        }

        jsonGenerator.writeEnd();
    }
}
