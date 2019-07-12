package org.acme.quickstart.entities;

import lombok.*;
import org.acme.quickstart.interceptor.JsonBackReference;
import org.acme.quickstart.interceptor.Portable;

import javax.json.bind.annotation.JsonbCreator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Portable
@Entity
@Builder
@NoArgsConstructor(onConstructor=@__(@JsonbCreator))
@AllArgsConstructor
public class Abbinamento extends OrdbarGenericEntity {

    @Id
    @GeneratedValue(generator = "ABBINAMENTO_SQ")
    @SequenceGenerator(name = "ABBINAMENTO_SQ", sequenceName = "ABBINAMENTO_SQ", allocationSize = 1)
    Long id;

    @NotNull
    Long idAbbinamentoOriginale;

    @NotNull
    String nome;

    @Enumerated(EnumType.STRING)
    Stato stato = Stato.APERTO;

    @ManyToOne
    @JoinColumn(name = "PIATTO_ORDINATO_ID")
    @JsonBackReference
    PiattoOrdinato piattoOrdinato;

    @Override
    protected void postPrePersist() {
        if (getStato() == null) {
            this.setStato(Stato.APERTO);
        }
    }

    @Override
    public void allineaRelazioni() {
        //non ha nulla da allineare
    }

    public enum Stato {
        APERTO, PRONTO, EVASO, CANCELLATO, SCARTATO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abbinamento that = (Abbinamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
