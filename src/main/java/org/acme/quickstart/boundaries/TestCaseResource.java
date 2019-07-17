package org.acme.quickstart.boundaries;

import org.acme.quickstart.entities.MonitorPostazione;
import org.acme.quickstart.entities.MonitorPostazionePK;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@Path("/test")
@ApplicationScoped
public class TestCaseResource {

    @Inject
    EntityManager em;

    @GET
    @Path("/persist/{abilitata}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public MonitorPostazione persist(@PathParam("abilitata") boolean abilitata) {
        MonitorPostazione monitorPostazione = new MonitorPostazione();

        monitorPostazione.setMonitorPostazionePK(new MonitorPostazionePK("D-571", new Random().nextLong(), "FORNO"));
        monitorPostazione.setAbilitata(abilitata);

        em.persist(monitorPostazione);

        return monitorPostazione;
    }

    @GET
    @Path("/merge")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public MonitorPostazione merge(MonitorPostazione entity) {
        MonitorPostazione rootEntity = em.merge(entity);
        return rootEntity;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public MonitorPostazione get(MonitorPostazione entity) {

        MonitorPostazione rootEntity = em.find(MonitorPostazione.class, entity.getMonitorPostazionePK());
        return rootEntity;
    }
}