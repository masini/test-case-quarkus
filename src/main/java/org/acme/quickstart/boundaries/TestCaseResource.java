package org.acme.quickstart.boundaries;

import org.acme.quickstart.entities.MonitorPostazione;
import org.acme.quickstart.entities.MonitorPostazionePK;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
@ApplicationScoped
public class TestCaseResource {

    @Inject
    EntityManager em;

    @GET
    @Path("/persist")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public MonitorPostazione persist() {
        MonitorPostazione monitorPostazione = new MonitorPostazione();
        monitorPostazione.setIdBar("D-571");
        monitorPostazione.setIdMonitor(1L);
        monitorPostazione.setSottoPreparazione("FORNO");
        monitorPostazione.setAbilitata(true);

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
    public MonitorPostazione get(MonitorPostazionePK pk) {

        MonitorPostazione rootEntity = em.find(MonitorPostazione.class, pk);
        return rootEntity;
    }
}