package org.acme.quickstart.boundaries;

import org.acme.quickstart.entities.Ordine;
import org.acme.quickstart.interceptor.ManagedAndBackReference;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceUnitUtil;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static javax.ws.rs.core.Response.Status.CONFLICT;

@Path("/ordini")
@ApplicationScoped
//@ManagedAndBackReference
public class OrdineResource {

    @Inject
    EntityManager em;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Ordine saveOrUpdateBL(@Valid Ordine entity) {

        if (entity.getBarcode() == null) {

            entity.setBarcode(generaOrdineId(new Random().nextLong()));
        }

        PersistenceUnitUtil persistenceUnitUtil = em.getEntityManagerFactory().getPersistenceUnitUtil();
        Object id = persistenceUnitUtil.getIdentifier(entity);

        if (id == null) {
            em.persist(entity);
        } else {
            try {
                entity = em.merge(entity);
                em.flush();
            } catch (OptimisticLockException e) {
                throw new WebApplicationException("errore in saveOrUpdate", e, CONFLICT);
            }
        }

        return entity;
    }


    @Inject
    HttpServletRequest request;

    public String generaOrdineId(Long numeroComanda) {

        DateFormat format = new SimpleDateFormat("ddMMYYhhmmss");
        String ipAddress = request.getRemoteAddr();
        if (ipAddress == null) {
            ipAddress = request.getHeader("X-FORWARDED-FOR");
        }

        String codiceOperatore = ipToHex(ipAddress);

        String id = String.format("9999%s%06d%s",
                codiceOperatore,
                numeroComanda,
                format.format(new Date()));

        return id;
    }

    private String ipToHex(String reqIpAddr) {
        StringBuilder hex = new StringBuilder();
        String[] part = reqIpAddr.split("[\\.,]");
        if (part.length < 4) {
            return "00000000";
        }
        //escludo il primo ottetto che è sempre 10
        for (int i = 1; i < 4; i++) {
            int decimal = Integer.parseInt(part[i]);
            if (decimal < 16) // Append a 0 to maintian 2 digits for every number
            {
                hex.append('0');
                hex.append(String.format("%01X", decimal));
            } else {
                hex.append(String.format("%01X", decimal));
            }
        }
        return hex.toString();
    }
}