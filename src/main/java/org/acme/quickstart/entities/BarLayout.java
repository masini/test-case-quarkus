package org.acme.quickstart.entities;

import lombok.*;

import javax.json.bind.annotation.JsonbCreator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


@NoArgsConstructor(onConstructor = @__(@JsonbCreator))
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@Entity
@EqualsAndHashCode
@Table(name = "BAR_LAYOUT_TEMP")
@NamedQueries({
        @NamedQuery(name = "BarLayout.checkBarLayoutById",
                query = "select count(l.bar) from BarLayout l  where l.bar.id = :id"),
        @NamedQuery(name = "BarLayout.findBySigla",
                query = "select l.bar from BarLayout l  where l.bar.sigla = :sigla"),
        @NamedQuery(name = "BarLayout.findAllSigla",
                query = "select l.bar from BarLayout l order by l.bar.sigla"),
        @NamedQuery(name = "BarLayout.findBySocieta",
                query = "select l.bar from BarLayout l where l.bar.societa = :societa"),
        @NamedQuery(name = "BarLayout.findBarLayoutWithApp",
                query = "select l from BarLayout l where l.hasApp = true order by l.bar.sigla"),
        @NamedQuery(name = "BarLayout.findBarWithoutLayout",
                query = "select b from Bar b where not b.id in (select l.id from BarLayout l) order by b.sigla"),
})
@ValidBarLayout
public class BarLayout implements Serializable {


    @Id
    @OneToOne
    @JoinColumn(name = "ID_BAR")
    Bar bar;

    @Version
    Long version;

    @Column(name = "MAX_COMANDE_VASSOIO")
    Long maxComandeVassoio;

    @Column(name = "FLUSSO")
    @Enumerated(EnumType.STRING)
    FlussoServizio flusso;

    @Column(name = "HAS_MONITOR_VASSOIO")
    boolean hasMonitorVassoio;

    @Column(name = "HAS_PDA_BEVANDE")
    boolean hasPdaBevande;

    @Column(name = "HAS_APP")
    boolean hasApp;

    long tolleranza;

    @NotNull
    @Pattern(regexp = "[0-2]?[0-9]:[0-9]{2}", message = "Formato orario valido mm:hh")
    @Column(name = "ORARIO_APERTURA_CUCINA")
    String orarioAperturaCucina;

    @NotNull
    @Pattern(regexp = "[0-2]?[0-9]:[0-9]{2}", message = "Formato orario valido mm:hh")
    @Column(name = "ORARIO_CHIUSURA_CUCINA")
    String orarioChiusuraCucina;

    @Pattern(regexp = "[0-2]?[0-9]:[0-9]{2}", message = "Formato orario valido mm:hh")
    @Column(name = "FINE_FASCIA_FREDDI")
    String fineFasciaFreddi;

    @NotNull
    @Pattern(regexp = "[0-2]?[0-9]:[0-9]{2}", message = "Formato orario valido mm:hh")
    @Column(name = "ORARIO_APERTURA_BAR")
    String orarioAperturaBar;


    public static BarLayout createNew() {
        return BarLayout.builder().flusso(FlussoServizio.FRONT).maxComandeVassoio(10L).tolleranza(20).build();
    }

}
