package org.acme.quickstart.boundaries;

import org.acme.quickstart.entities.FirstChild;
import org.acme.quickstart.entities.RootEntity;
import org.acme.quickstart.entities.SecondChild;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collections;

@Path("/test")
@ApplicationScoped
public class TestCaseResource {

    @Inject
    EntityManager em;

    @GET
    public void get() {
        RootEntity updateBL = create();
        mergeIt(updateBL);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public RootEntity create() {
        SecondChild sc1 = new SecondChild();
        SecondChild sc2 = new SecondChild();

        FirstChild firstChild = new FirstChild();

        firstChild.getSecondChildren().add(sc2);
        firstChild.getSecondChildren().add(sc1);

        RootEntity rootEntity = new RootEntity();

        rootEntity.setFirstChildren(Collections.singletonList(firstChild));

        rootEntity.setBarcode("barcode");

        return em.merge(rootEntity);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public RootEntity mergeIt(RootEntity entity) {

        RootEntity r = em.createQuery("select r from RootEntity r join fetch r.firstChildren fc join fetch fc.secondChildren where r.barcode = :barcode", RootEntity.class).setParameter("barcode", entity.getBarcode()).getSingleResult();

        RootEntity rootEntity = em.merge(entity);
        return rootEntity;
    }

}