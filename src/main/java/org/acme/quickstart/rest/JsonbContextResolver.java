package org.acme.quickstart.rest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class JsonbContextResolver implements ContextResolver<Jsonb> {

    @Inject
    Jsonb jsonb;

    @PostConstruct
    void initJsonb() {
        if( jsonb==null ) {
            SmartJsonb smartJsonb = new SmartJsonb();
            smartJsonb.creaSerializers();

            jsonb = smartJsonb;
        }
    }

    @Override
    public Jsonb getContext(Class<?> type) {
        return jsonb;
    }

}
