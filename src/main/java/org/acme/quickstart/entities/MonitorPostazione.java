package org.acme.quickstart.entities;

import javax.persistence.*;


@Entity
@Table(name = "MONITOR_POSTAZIONI")
public class MonitorPostazione {

    @EmbeddedId
    MonitorPostazionePK monitorPostazionePK;

    @Column(name = "ABILITATA")
    boolean abilitata;

    public MonitorPostazionePK getMonitorPostazionePK() {
        return monitorPostazionePK;
    }

    public void setMonitorPostazionePK(MonitorPostazionePK monitorPostazionePK) {
        this.monitorPostazionePK = monitorPostazionePK;
    }

    public boolean isAbilitata() {
        return abilitata;
    }

    public void setAbilitata(boolean abilitata) {
        this.abilitata = abilitata;
    }
}
