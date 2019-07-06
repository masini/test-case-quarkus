package org.acme.quickstart.rest;

import org.eclipse.yasson.FieldAccessStrategy;
import org.eclipse.yasson.internal.JsonBindingBuilder;
import org.glassfish.json.JsonProviderImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static java.util.Locale.ITALIAN;
import static javax.json.bind.annotation.JsonbDateFormat.TIME_IN_MILLIS;

@Provider
@ApplicationScoped
public class JsonbContextResolver implements ContextResolver<Jsonb> {

    @Override
    public Jsonb getContext(Class<?> type) {

        JsonbConfig config = new JsonbConfig()
                .withPropertyVisibilityStrategy(new FieldAccessStrategy())
                ;

        return JsonbBuilder.create(config);
    }

}
