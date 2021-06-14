package co.edu.icesi.ci.tallerfinal.front.model.classes;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CheckMeasur {

    private static final long serialVersionUID = 1L;

    private CheckMeasurPK id;

    private BigDecimal measvalue;

    private Measurement measurement;

    private Physicalcheckup physicalcheckup;

    public CheckMeasur() {
    }

    public CheckMeasurPK getId() {
        return this.id;
    }

    public Measurement getMeasurement() {
        return this.measurement;
    }

    public BigDecimal getMeasvalue() {
        return this.measvalue;
    }

    public Physicalcheckup getPhysicalcheckup() {
        return this.physicalcheckup;
    }

    public void setId(CheckMeasurPK id) {
        this.id = id;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public void setMeasvalue(BigDecimal measvalue) {
        this.measvalue = measvalue;
    }

    public void setPhysicalcheckup(Physicalcheckup physicalcheckup) {
        this.physicalcheckup = physicalcheckup;
    }

}
