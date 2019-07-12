package org.acme.quickstart.boundaries;

import org.acme.quickstart.entities.Abbinamento;
import org.acme.quickstart.entities.Ordine;
import org.acme.quickstart.entities.PiattoOrdinato;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Collections;

@Path("/ordini")
@ApplicationScoped
public class OrdineResource {

    @Inject
    EntityManager em;

    @GET
    public void get() {
        Abbinamento abbFreddo = new Abbinamento();
        Abbinamento abbCaldo = new Abbinamento();

        PiattoOrdinato piattoOrdinato = new PiattoOrdinato();

        piattoOrdinato.getAbbinamenti().add(abbCaldo);
        piattoOrdinato.getAbbinamenti().add(abbFreddo);

        Ordine ordine = Ordine.builder().barcode("barcode")
                .build();

        piattoOrdinato.setOrdine(ordine);
        ordine.setPiattiOrdinati(Collections.singletonList(piattoOrdinato));

        Ordine updateBL = saveOrUpdateBL(ordine);
        saveOrUpdateBL(updateBL);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Ordine saveOrUpdateBL(@Valid Ordine entity) {

        return em.merge(entity);
    }

}