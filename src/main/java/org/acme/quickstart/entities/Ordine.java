package org.acme.quickstart.entities;

import lombok.*;
import org.acme.quickstart.interceptor.JsonManagedReference;
import org.acme.quickstart.interceptor.Portable;
import org.acme.quickstart.interceptor.ValidOrdine;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.json.bind.annotation.JsonbDateFormat.TIME_IN_MILLIS;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(onConstructor = @__(@JsonbCreator))
@Portable
@Entity
@EqualsAndHashCode(of = "barcode")
@Table(name = "ORDINE")
@ValidOrdine
public class Ordine extends OrdbarGenericEntity {

    @Id
    String barcode;

    @NotNull
    String stato;

    @Transient
    Bar bar;

    String numeroComanda;

    @Transient
    boolean prioritario;


    //  Usare queste annotazioni commentate per far salvare automaticamente l'oggetto nelle relazioni
//  N.B. farà una update per ognuno
//  @OneToMany(cascade = CascadeType.ALL)
//  @JoinColumn(name = "ORDINE_ID")
//  Jackson tramite le annotazioni @JsonManagedReference e @JsonBackReference fa il set in automatico. Nei test non usiamo jackson!
    //@ValidOrdine
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordine")
    @JsonManagedReference
    List<PiattoOrdinato> piattiOrdinati = new ArrayList<>();

    //@ValidOrdine
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ORDINE_MENU", joinColumns = @JoinColumn(name = "ORDINE_ID"))
    List<Menu> menus = new ArrayList<>();

    @NotNull
    String canale;

    @NotNull
    Long idCatalogoOriginale;

    String cartaFidaty;
    String convenzione;
    boolean asporto;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(TIME_IN_MILLIS)
    Date dataChiusura;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(TIME_IN_MILLIS)
    Date dataPresaInCarico;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(TIME_IN_MILLIS)
    Date dataCheckin;

    @Column(name = "ID_MONITOR")
    Long idMonitor;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(TIME_IN_MILLIS)
    private Date inizioFascia;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(TIME_IN_MILLIS)
    private Date fineFascia;

    Long numeroPersone;

    @Override
    public void allineaRelazioni() {

    }
}
