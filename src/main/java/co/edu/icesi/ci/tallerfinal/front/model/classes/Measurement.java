package co.edu.icesi.ci.tallerfinal.front.model.classes;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class Measurement {

    private long measId;

    private String measDescription;

    private BigDecimal measMaxthreshold;

    private BigDecimal measMinthreshold;

    private String measName;

    private String measUnit;

    private List<CheckMeasur> checkMeasurs;

    private Institution institution;

    public Measurement() {
    }

    public long getMeasId() {
        return this.measId;
    }

    public void setMeasId(long measId) {
        this.measId = measId;
    }

    public String getMeasDescription() {
        return this.measDescription;
    }

    public void setMeasDescription(String measDescription) {
        this.measDescription = measDescription;
    }

    public BigDecimal getMeasMaxthreshold() {
        return this.measMaxthreshold;
    }

    public void setMeasMaxthreshold(BigDecimal measMaxthreshold) {
        this.measMaxthreshold = measMaxthreshold;
    }

    public BigDecimal getMeasMinthreshold() {
        return this.measMinthreshold;
    }

    public void setMeasMinthreshold(BigDecimal measMinthreshold) {
        this.measMinthreshold = measMinthreshold;
    }

    public String getMeasName() {
        return this.measName;
    }

    public void setMeasName(String measName) {
        this.measName = measName;
    }

    public String getMeasUnit() {
        return this.measUnit;
    }

    public void setMeasUnit(String measUnit) {
        this.measUnit = measUnit;
    }

    public List<CheckMeasur> getCheckMeasurs() {
        return this.checkMeasurs;
    }

    public void setCheckMeasurs(List<CheckMeasur> checkMeasurs) {
        this.checkMeasurs = checkMeasurs;
    }

    public CheckMeasur addCheckMeasur(CheckMeasur checkMeasur) {
        getCheckMeasurs().add(checkMeasur);
        checkMeasur.setMeasurement(this);

        return checkMeasur;
    }

    public CheckMeasur removeCheckMeasur(CheckMeasur checkMeasur) {
        getCheckMeasurs().remove(checkMeasur);
        checkMeasur.setMeasurement(null);

        return checkMeasur;
    }

    public Institution getInstitution() {
        return this.institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

}
