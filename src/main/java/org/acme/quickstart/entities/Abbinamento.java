package org.acme.quickstart.entities;

import lombok.*;

import javax.json.bind.annotation.JsonbCreator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter

@Entity
@Builder
@NoArgsConstructor(onConstructor=@__(@JsonbCreator))
@AllArgsConstructor
public class Abbinamento extends OrdbarGenericEntity {

    @Id
    @GeneratedValue(generator = "ABBINAMENTO_SQ")
    @SequenceGenerator(name = "ABBINAMENTO_SQ", sequenceName = "ABBINAMENTO_SQ", allocationSize = 1)
    Long id;

    @ManyToOne
    @JoinColumn(name = "PIATTO_ORDINATO_ID")
    PiattoOrdinato piattoOrdinato;

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
