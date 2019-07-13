package org.acme.quickstart.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "barcode")
@Entity
public class RootEntity extends GenericEntity {

    @Id
    String barcode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rootEntity")
    List<FirstChild> firstChildren = new ArrayList<>();

}


