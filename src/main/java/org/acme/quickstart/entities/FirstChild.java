package org.acme.quickstart.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FirstChild {

    @Id
    @GeneratedValue(generator = "FIRST_CHILD_SQ")
    @SequenceGenerator(name = "FIRST_CHILD_SQ")
    Long id;

    @Version
    long version;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<SecondChild> secondChildren = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SecondChild> getSecondChildren() {
        return secondChildren;
    }

    public void setSecondChildren(Set<SecondChild> secondChildren) {
        this.secondChildren = secondChildren;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
