package org.acme.quickstart.boundaries;

import org.acme.quickstart.entities.Bar;
import org.acme.quickstart.entities.BarLayout;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.Response.Status.CONFLICT;

@Path("/layout")
@ApplicationScoped
public class BarLayoutResource {

    @Inject
    EntityManager em;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public BarLayout saveOrUpdateBL(@Valid BarLayout entity) {

        BarLayout oldValue = em.find(BarLayout.class,entity.getBar().getId());

        if (oldValue == null) {
            em.persist(entity);
        } else {
            try {
                entity = em.merge(entity);
                em.flush();
            } catch (OptimisticLockException e) {
                throw new WebApplicationException(CONFLICT);
            }
        }

        return entity;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("bar")
    @Transactional
    public Bar saveOrUpdate(@Valid Bar entity) {

        Bar bar = em.find(Bar.class, entity.getId());
        if( bar==null ) {
            em.persist(entity);
            em.flush();
        }

        return entity;
    }
}