package org.acme.quickstart.rest;

import io.quarkus.arc.Unremovable;
import org.acme.quickstart.entities.PiattoOrdinato;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

import static org.acme.quickstart.rest.SimpleJsonGenerator.simpleGenerator;

@ApplicationScoped
@Unremovable
public class PiattoOrdinatoSerializer implements JsonbSerializer<PiattoOrdinato> {

    @Inject
    OrdineSerializer ordineSerializer;

    @Inject
    GenericEntitySerializer genericEntitySerializer;

    @Override
    public void serialize(PiattoOrdinato piattoOrdinato, JsonGenerator jsonGenerator, SerializationContext ctx) {

        jsonGenerator.writeStartObject();

        genericEntitySerializer.serialize(piattoOrdinato, jsonGenerator, ctx);

        simpleGenerator(jsonGenerator, ctx)
                .writeIfNotNull("id", piattoOrdinato.getId())
                .writeIfNotNull("stato", piattoOrdinato.getStato())
                .writeIfNotNull("idMonitor", piattoOrdinato.getIdMonitor())
                .writeIfNotNull("uuidMenu", piattoOrdinato.getUuidMenu())
                .writeIfNotNull("abbinamenti", piattoOrdinato.getAbbinamenti())
                .writeIfNotNull("piatto", piattoOrdinato.getPiatto())
                .writeIfNotNull("quantita", piattoOrdinato.getQuantita())
                .writeIfNotNull("nota", piattoOrdinato.getNota());

        if(piattoOrdinato.getOrdine()!=null) {
            jsonGenerator.writeKey("ordine");
            ordineSerializer.serialize(piattoOrdinato.getOrdine(), jsonGenerator, ctx);
        }
        jsonGenerator.writeEnd();
    }
}
