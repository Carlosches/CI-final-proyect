package co.edu.icesi.ci.tallerfinal.front.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;

public class Institutioncampus {

    private static final long serialVersionUID = 1L;

    private long instcamId;

    private String instcamName;

    private BigDecimal instcamOccupation;

    private Institution institution;

    @JsonIgnore
    private List<Visit> visits;

    public Institutioncampus() {
    }

    public long getInstcamId() {
        return this.instcamId;
    }

    public void setInstcamId(long instcamId) {
        this.instcamId = instcamId;
    }

    public String getInstcamName() {
        return this.instcamName;
    }

    public void setInstcamName(String instcamName) {
        this.instcamName = instcamName;
    }

    public BigDecimal getInstcamOccupation() {
        return this.instcamOccupation;
    }

    public void setInstcamOccupation(BigDecimal instcamOccupation) {
        this.instcamOccupation = instcamOccupation;
    }

    public Institution getInstitution() {
        return this.institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public List<Visit> getVisits() {
        return this.visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Visit addVisit(Visit visit) {
        getVisits().add(visit);
        visit.setInstitutioncampus(this);

        return visit;
    }

    public Visit removeVisit(Visit visit) {
        getVisits().remove(visit);
        visit.setInstitutioncampus(null);

        return visit;
    }
}
