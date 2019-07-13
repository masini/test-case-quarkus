package org.acme.quickstart.entities;

import javax.persistence.*;

@Entity
public class SecondChild extends GenericEntity {

    @Id
    @GeneratedValue(generator = "SECOND_CHILD_SQ")
    @SequenceGenerator(name = "SECOND_CHILD_SQ")
    Long id;

    @ManyToOne
    FirstChild firstChild;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FirstChild getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(FirstChild firstChild) {
        this.firstChild = firstChild;
    }
}
