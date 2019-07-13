package org.acme.quickstart.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RootEntity {

    @Id
    String barcode;

    @Version
    long version;

    @OneToMany(cascade = CascadeType.ALL)
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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}


