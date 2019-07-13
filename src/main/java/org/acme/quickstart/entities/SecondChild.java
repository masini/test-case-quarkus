package org.acme.quickstart.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class SecondChild extends GenericEntity {

    @Id
    @GeneratedValue(generator = "SECOND_CHILD_SQ")
    @SequenceGenerator(name = "SECOND_CHILD_SQ")
    Long id;

    @ManyToOne
    FirstChild firstChild;
}
