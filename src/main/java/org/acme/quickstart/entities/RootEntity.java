package org.acme.quickstart.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RootEntity extends GenericEntity {

    @Id
    String barcode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rootEntity")
    List<FirstChild> firstChildren = new ArrayList<>();

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<FirstChild> getFirstChildren() {
        return firstChildren;
    }

    public void setFirstChildren(List<FirstChild> firstChildren) {
        this.firstChildren = firstChildren;
    }
}


