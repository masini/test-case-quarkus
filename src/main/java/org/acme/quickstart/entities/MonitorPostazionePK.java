package org.acme.quickstart.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class MonitorPostazionePK implements Serializable {
    @Column(name = "ID_BAR")
    String idBar;

    @Column(name = "ID_MONITOR")
    Long idMonitor;

    @Column(name = "SOTTOPREPARAZIONE")
    String sottoPreparazione;

    public MonitorPostazionePK() {
    }

    public MonitorPostazionePK(String idBar, Long idMonitor, String sottoPreparazione) {
        this.idBar = idBar;
        this.idMonitor = idMonitor;
        this.sottoPreparazione = sottoPreparazione;
    }

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
}
