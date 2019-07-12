package org.acme.quickstart.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.quickstart.interceptor.Portable;

import javax.json.bind.annotation.JsonbCreator;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(onConstructor=@__(@JsonbCreator))
@Portable
@Embeddable
public class Piatto {

    @NotNull
    String nome;

    @NotNull
    String ean;

    @NotNull
    String prezzo;

    @NotNull
    Long idArticoloOriginale;

    String descrizioneStampa;

    @NotNull
    @Enumerated(EnumType.STRING)
    Preparazione preparazione;

    String sottoPreparazione;

    public enum Preparazione {
        CUCINA("Cucina"),
        VASSOIO("Vassoio"),
        A_VISTA("A vista");

        String descrizione;

        Preparazione(String label) {
            this.descrizione = label;
        }

        public static List<Preparazione> getPreparazioni(Collection<String> strings) {
            List<Preparazione> preparazioni = new ArrayList<>();
            for (String s : strings) {
                preparazioni.add(Preparazione.valueOf(s));
            }
            return preparazioni;
        }

        public String getDescrizione() {
            return descrizione;
        }
    }

}
