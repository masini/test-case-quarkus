package org.acme.quickstart.boundaries;

import org.acme.quickstart.entities.FirstChild;
import org.acme.quickstart.entities.RootEntity;
import org.acme.quickstart.entities.SecondChild;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
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

        sc1.setFirstChild(firstChild);
        sc2.setFirstChild(firstChild);
        firstChild.getSecondChildren().add(sc2);
        firstChild.getSecondChildren().add(sc1);

        RootEntity rootEntity = new RootEntity();

        firstChild.setRootEntity(rootEntity);
        rootEntity.setFirstChildren(Collections.singletonList(firstChild));

        rootEntity.setBarcode("barcode");

        return em.merge(rootEntity);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public RootEntity mergeIt(@Valid RootEntity entity) {

        return em.merge(entity);
    }

}