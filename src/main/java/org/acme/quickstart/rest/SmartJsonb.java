package org.acme.quickstart.rest;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.Config;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import javax.json.bind.serializer.JsonbSerializer;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.Locale.ITALIAN;
import static javax.json.bind.annotation.JsonbDateFormat.TIME_IN_MILLIS;

@Slf4j
@ApplicationScoped
@Alternative
@Priority(1000)
public class SmartJsonb implements Jsonb {

    private Map<Type, Jsonb> configurations;

    Jsonb defaultSerializer;

    @Inject
    Config config;

    private final static String VIEW="jsonb.view.";

    private final boolean seiUnVecchioSviluppatore = true;

    @PostConstruct
    void creaSerializers() {

        configurations = Collections.unmodifiableMap(new HashMap<Type, Jsonb>() {
            {
                if (seiUnVecchioSviluppatore) {
                    for(String name: config.getPropertyNames()) {
                        if(name.startsWith(VIEW)) {
                            addToCollection(name);
                        }
                    }
                } else {
                    StreamSupport.stream(config.getPropertyNames().spliterator(), false).filter(name->name.startsWith(VIEW)).forEach(name -> {
                        addToCollection(name);
                    });
                }
            }

            private final void addToCollection(String name) {
                try {
                    Class keyClass = Class.forName(name.substring(VIEW.length()));
                    Class[] valueClasses = config.getValue(name, Class[].class);

                    put(keyClass, createJsonb(valueClasses));
                } catch (Exception e) {
                    if(e instanceof ClassNotFoundException || e.getCause() instanceof ClassNotFoundException) {
                        log.error("*** non riesco a mappare la view {}, ClassNotFoundException ***", name);
                    } else {
                        throw new RuntimeException("errore durante la creazione della jsonb.view "+name, e);
                    }
                }
            }
        });

        defaultSerializer = createJsonb();
    }

    //static per permettere la creazione anche col bug di injection nei provider JAX-RS, si potrà levare appena risolto
    static Jsonb createJsonb() {
        return createJsonb(new JsonbSerializer[]{});
    }

    static Jsonb createJsonb(Class<? extends JsonbSerializer>...serializers) {
        JsonbSerializer[] serializersArray = Arrays.stream(serializers).map(serializer -> CDI.current().select(serializer).get()).toArray(JsonbSerializer[]::new);
        return createJsonb(serializersArray);
    }

    static Jsonb createJsonb(JsonbSerializer...serializers) {
        JsonbConfig config = new JsonbConfig()
                .withFormatting(false)
                .withDateFormat(TIME_IN_MILLIS, ITALIAN);

        for (JsonbSerializer serializer : serializers) {
            config.withSerializers(serializer);
        }

        return JsonbBuilder.create(config);
    }

    @Override
    public <T> T fromJson(String str, Class<T> type) throws JsonbException {
        return defaultSerializer.fromJson(str, type);
    }

    @Override
    public <T> T fromJson(String str, Type runtimeType) throws JsonbException {
        return defaultSerializer.fromJson(str, runtimeType);
    }

    @Override
    public <T> T fromJson(Reader reader, Class<T> type) throws JsonbException {
        return defaultSerializer.fromJson(reader, type);
    }

    @Override
    public <T> T fromJson(Reader reader, Type runtimeType) throws JsonbException {
        return defaultSerializer.fromJson(reader, runtimeType);
    }

    @Override
    public <T> T fromJson(InputStream stream, Class<T> type) throws JsonbException {
        return defaultSerializer.fromJson(stream, type);
    }

    @Override
    public <T> T fromJson(InputStream stream, Type runtimeType) throws JsonbException {
        return defaultSerializer.fromJson(stream, runtimeType);
    }

    Jsonb jsonbFor(Object object) {

        Objects.requireNonNull(object, "Non esiste il serializer di null!");

        Jsonb jsonb = configurations.get(object.getClass());

        if( jsonb==null) {
            if( object.getClass().isArray() ) {
                jsonb = configurations.get(object.getClass().getComponentType());
            } else if(object instanceof Collection){
                Collection list = (Collection) object;
                jsonb = (Jsonb) list.stream().findFirst().map(first -> configurations.get(first.getClass())).orElse(defaultSerializer);
            } else {
                jsonb = defaultSerializer;
            }
        }

        return jsonb;
    }

    Jsonb jsonbFor(Type type) {

        Objects.requireNonNull(type, "Non esiste il serializer del type null!");

        Jsonb jsonb = configurations.get(type);

        if( jsonb==null) {
            jsonb = defaultSerializer;
        }

        return jsonb;
    }

    @Override
    public String toJson(Object object) throws JsonbException {
        return jsonbFor(object).toJson(object);
    }

    @Override
    public String toJson(Object object, Type runtimeType) throws JsonbException {
        return jsonbFor(runtimeType).toJson(object, runtimeType);
    }

    @Override
    public void toJson(Object object, Writer writer) throws JsonbException {
        jsonbFor(object).toJson(object, writer);
    }

    @Override
    public void toJson(Object object, Type runtimeType, Writer writer) throws JsonbException {
        jsonbFor(runtimeType).toJson(object, writer);

    }

    @Override
    public void toJson(Object object, OutputStream stream) throws JsonbException {
        jsonbFor(object).toJson(object, stream);
    }

    @Override
    public void toJson(Object object, Type runtimeType, OutputStream stream) throws JsonbException {
        jsonbFor(runtimeType).toJson(object, stream);
    }

    @Override
    public void close() throws Exception {
        //TODO: questo bean è ApplicationScoped, qui non deve MAI fare niente !
    }
}
