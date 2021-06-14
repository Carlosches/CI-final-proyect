package co.edu.icesi.ci.tallerfinal.front.model.classes;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class Visit {

    private static final long serialVersionUID = 1L;

    private long visitId;

    private String visitDetail;

    private Date visitEntrancedate;

    private Date visitExitdate;


    private List<Physicalcheckup> physicalcheckups;


    private Institutioncampus institutioncampus;

    private Person person;

    public Visit() {
    }

    public long getVisitId() {
        return this.visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public String getVisitDetail() {
        return this.visitDetail;
    }

    public void setVisitDetail(String visitDetail) {
        this.visitDetail = visitDetail;
    }

    public Date getVisitEntrancedate() {
        return this.visitEntrancedate;
    }

    public void setVisitEntrancedate(Date visitEntrancedate) {
        this.visitEntrancedate = visitEntrancedate;
    }

    public Date getVisitExitdate() {
        return this.visitExitdate;
    }

    public void setVisitExitdate(Date visitExitdate) {
        this.visitExitdate = visitExitdate;
    }

    public List<Physicalcheckup> getPhysicalcheckups() {
        return this.physicalcheckups;
    }

    public void setPhysicalcheckups(List<Physicalcheckup> physicalcheckups) {
        this.physicalcheckups = physicalcheckups;
    }

    public Physicalcheckup addPhysicalcheckup(Physicalcheckup physicalcheckup) {
        getPhysicalcheckups().add(physicalcheckup);
        physicalcheckup.setVisit(this);

        return physicalcheckup;
    }

    public Physicalcheckup removePhysicalcheckup(Physicalcheckup physicalcheckup) {
        getPhysicalcheckups().remove(physicalcheckup);
        physicalcheckup.setVisit(null);

        return physicalcheckup;
    }

    public Institutioncampus getInstitutioncampus() {
        return this.institutioncampus;
    }

    public void setInstitutioncampus(Institutioncampus institutioncampus) {
        this.institutioncampus = institutioncampus;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
