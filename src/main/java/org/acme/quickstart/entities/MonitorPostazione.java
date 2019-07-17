package org.acme.quickstart.entities;

import javax.persistence.*;


@Entity
@Table(name = "MONITOR_POSTAZIONI")
@IdClass(MonitorPostazionePK.class)
@NamedQueries({
        @NamedQuery(name = "MonitorPostazione.findPostazioniByBar",
                query = "select m from MonitorPostazione m where m.idBar = :idBar"),
        @NamedQuery(name = "MonitorPostazione.findPostazioniByBarAndMonitor",
                query = "select m from MonitorPostazione m where m.idBar = :idBar and m.idMonitor = :idMonitor"),
        @NamedQuery(name = "MonitorPostazione.findPostazioniAbilitataByBarAndPostazione",
                query = "select m from MonitorPostazione m where m.idBar = :idBar and m.sottoPreparazione = :sottopreparazione and m.abilitata = true "),
})
public class MonitorPostazione {
    @Id
    @Column(name = "ID_BAR")
    String idBar;

    @Id
    @Column(name = "ID_MONITOR")
    Long idMonitor;

    @Id
    @Column(name = "SOTTOPREPARAZIONE")
    String sottoPreparazione;

    @Column(name = "ABILITATA")
    boolean abilitata;

    public String getIdBar() {
        return idBar;
    }

    public void setIdBar(String idBar) {
        this.idBar = idBar;
    }

    public Long getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(Long idMonitor) {
        this.idMonitor = idMonitor;
    }

    public String getSottoPreparazione() {
        return sottoPreparazione;
    }

    public void setSottoPreparazione(String sottoPreparazione) {
        this.sottoPreparazione = sottoPreparazione;
    }

    public boolean isAbilitata() {
        return abilitata;
    }

    public void setAbilitata(boolean abilitata) {
        this.abilitata = abilitata;
    }
}
