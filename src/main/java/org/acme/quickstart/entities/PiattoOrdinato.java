package org.acme.quickstart.entities;

import lombok.*;

import javax.json.bind.annotation.JsonbCreator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(onConstructor = @__(@JsonbCreator))
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PIATTO_ORDINATO")
public class PiattoOrdinato extends OrdbarGenericEntity {

    @Id
    @GeneratedValue(generator = "PIATTO_ORDINATO_SQ")
    @SequenceGenerator(name = "PIATTO_ORDINATO_SQ", sequenceName = "PIATTO_ORDINATO_SQ", allocationSize = 1)
    Long id;

    @ManyToOne
    @JoinColumn(name = "ORDINE_ID", referencedColumnName = "BARCODE")
    Ordine ordine;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "piattoOrdinato", orphanRemoval = true)
    List<Abbinamento> abbinamenti = new ArrayList<>();
}
