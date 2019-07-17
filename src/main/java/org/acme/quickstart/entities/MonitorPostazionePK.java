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


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MonitorPostazionePK)) return false;
        final MonitorPostazionePK other = (MonitorPostazionePK) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$idBar = this.getIdBar();
        final Object other$idBar = other.getIdBar();
        if (this$idBar == null ? other$idBar != null : !this$idBar.equals(other$idBar)) return false;
        final Object this$idMonitor = this.getIdMonitor();
        final Object other$idMonitor = other.getIdMonitor();
        if (this$idMonitor == null ? other$idMonitor != null : !this$idMonitor.equals(other$idMonitor)) return false;
        final Object this$sottoPreparazione = this.getSottoPreparazione();
        final Object other$sottoPreparazione = other.getSottoPreparazione();
        if (this$sottoPreparazione == null ? other$sottoPreparazione != null : !this$sottoPreparazione.equals(other$sottoPreparazione))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MonitorPostazionePK;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $idBar = this.getIdBar();
        result = result * PRIME + ($idBar == null ? 43 : $idBar.hashCode());
        final Object $idMonitor = this.getIdMonitor();
        result = result * PRIME + ($idMonitor == null ? 43 : $idMonitor.hashCode());
        final Object $sottoPreparazione = this.getSottoPreparazione();
        result = result * PRIME + ($sottoPreparazione == null ? 43 : $sottoPreparazione.hashCode());
        return result;
    }
}
