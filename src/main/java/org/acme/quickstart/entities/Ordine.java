package org.acme.quickstart.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "barcode")
@Entity
@Table(name = "ORDINE")
public class Ordine extends OrdbarGenericEntity {

    @Id
    String barcode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordine")
    List<PiattoOrdinato> piattiOrdinati = new ArrayList<>();

}


