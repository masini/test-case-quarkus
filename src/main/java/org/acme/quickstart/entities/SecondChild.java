package org.acme.quickstart.entities;

import javax.persistence.*;

@Entity
public class SecondChild {

    @Id
    @GeneratedValue(generator = "SECOND_CHILD_SQ")
    @SequenceGenerator(name = "SECOND_CHILD_SQ")
    Long id;

    @Version
    long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
