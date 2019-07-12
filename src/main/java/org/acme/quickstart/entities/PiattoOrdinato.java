package org.acme.quickstart.entities;

import lombok.*;
import org.acme.quickstart.interceptor.JsonBackReference;
import org.acme.quickstart.interceptor.JsonManagedReference;
import org.acme.quickstart.interceptor.Portable;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.json.bind.annotation.JsonbDateFormat.TIME_IN_MILLIS;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(onConstructor = @__(@JsonbCreator))
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Portable
@Entity
@Table(name = "PIATTO_ORDINATO")
public class PiattoOrdinato extends OrdbarGenericEntity {

    @Id
    @GeneratedValue(generator = "PIATTO_ORDINATO_SQ")
    @SequenceGenerator(name = "PIATTO_ORDINATO_SQ", sequenceName = "PIATTO_ORDINATO_SQ", allocationSize = 1)
    Long id;

    String stato;

    @Valid
    @NotNull
    @Embedded
    Piatto piatto;

    @ManyToOne
    @JoinColumn(name = "ORDINE_ID", referencedColumnName = "BARCODE")
    @JsonBackReference
    Ordine ordine;

    @NotNull
    Long quantita;
    long quantitaEvasa;
    String nota;

    public List<Abbinamento> getAbbinamenti() {
        return abbinamenti;
    }

    public void setAbbinamenti(List<Abbinamento> abbinamenti) {
        this.abbinamenti = abbinamenti;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "piattoOrdinato", orphanRemoval = true)
    @JsonManagedReference
    List<Abbinamento> abbinamenti = new ArrayList<>();

    String uuidMenu;

    boolean asporto;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(TIME_IN_MILLIS)
    Date dataPresaInCarico;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(TIME_IN_MILLIS)
    Date dataPronto;

    @Column(name = "ID_MONITOR")
    Long idMonitor;

    @Override
    public void allineaRelazioni() {

    }
}
