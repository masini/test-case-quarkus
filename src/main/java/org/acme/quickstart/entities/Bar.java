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
@NamedQueries({
        @NamedQuery(name = "Bar.findBySigla",
                query = "select b from Bar b where b.sigla = :sigla"),
        @NamedQuery(name = "Bar.findAllSigla",
                query = "select b.sigla from Bar b  inner join BarLayout l  on b.id = l.bar order by b.sigla"),
        @NamedQuery(name = "Bar.find",
                query = "select b from Bar b where b.sigla = :sigla")
})
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
