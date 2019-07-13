package org.acme.quickstart.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class GenericEntity {

    @Temporal(TemporalType.TIMESTAMP)
    Date insertDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date lastUpdate;

    @Version
    long version;
}
