package org.acme.quickstart.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FirstChild extends GenericEntity {

    @Id
    @GeneratedValue(generator = "FIRST_CHILD_SQ")
    @SequenceGenerator(name = "FIRST_CHILD_SQ")
    Long id;

    @ManyToOne
    RootEntity rootEntity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "firstChild", orphanRemoval = true)
    List<SecondChild> secondChildren = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RootEntity getRootEntity() {
        return rootEntity;
    }

    public void setRootEntity(RootEntity rootEntity) {
        this.rootEntity = rootEntity;
    }

    public List<SecondChild> getSecondChildren() {
        return secondChildren;
    }

    public void setSecondChildren(List<SecondChild> secondChildren) {
        this.secondChildren = secondChildren;
    }
}
