package org.acme.quickstart.rest;

import org.acme.quickstart.entities.OrdbarGenericEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

import static org.acme.quickstart.rest.SimpleJsonGenerator.simpleGenerator;

@ApplicationScoped
public class GenericEntitySerializer {
    public void serialize(OrdbarGenericEntity genericEntity, JsonGenerator jsonGenerator, SerializationContext ctx) {
        simpleGenerator(jsonGenerator, ctx)
                .writeIfNotNull("version", genericEntity.getVersion())
                .writeIfNotNull("dataAggiornamento", genericEntity.getDataAggiornamento()!=null?genericEntity.getDataAggiornamento().getTime(): null)
                .writeIfNotNull("dataInserimento", genericEntity.getDataInserimento() != null ? genericEntity.getDataInserimento().getTime() : null);
    }
}
