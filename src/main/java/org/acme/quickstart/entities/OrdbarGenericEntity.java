package org.acme.quickstart.entities;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.util.Date;

import static javax.json.bind.annotation.JsonbDateFormat.TIME_IN_MILLIS;

@MappedSuperclass
@Getter
@Setter
public abstract class OrdbarGenericEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(TIME_IN_MILLIS)
    Date dataInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(TIME_IN_MILLIS)
    Date dataAggiornamento;

    @Version
    long version;

    @PrePersist
    protected final void onPrePersist() {
        setDataInserimento(new Date());
        setDataAggiornamento(new Date());
        allineaRelazioni();

        postPrePersist();
    }

    protected void postPrePersist() {}

    @PreUpdate
    protected final void onPreUpdate() {
        setDataAggiornamento(new Date());
        allineaRelazioni();

        postPreUpdate();
    }

    protected void postPreUpdate() {}


    public abstract void allineaRelazioni();

}
