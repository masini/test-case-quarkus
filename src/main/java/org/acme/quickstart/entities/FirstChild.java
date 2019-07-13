package org.acme.quickstart.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
}
