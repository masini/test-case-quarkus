package org.acme.quickstart.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbCreator;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(onConstructor=@__(@JsonbCreator))
@Entity
@Table(name = "BAR_TEMP")
public class Bar implements Serializable {

    @Id
    String id;

    String descrizione;
    String societa;
    String filiale;
    Long numero;
    String sigla;
    Long zona;

    @Override
    public String toString() {
        return id;
    }
}
