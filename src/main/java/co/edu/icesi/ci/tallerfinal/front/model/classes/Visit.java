package co.edu.icesi.ci.tallerfinal.front.model.classes;

import co.edu.icesi.ci.tallerfinal.back.groups.AddVisit;
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

    @Size(min =5, message="El tamaño debe ser mayor a 5",groups = AddVisit.class)
    private String visitDetail;

    @NotNull(message="La fecha de ingreso es obligatoria", groups=AddVisit.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @FutureOrPresent(message="La fecha de ingreso debe ser en el futuro", groups=AddVisit.class)
    private Date visitEntrancedate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message="La fecha de egreso debe ser en el futuro", groups=AddVisit.class)
    private Date visitExitdate;


    private List<Physicalcheckup> physicalcheckups;

    @NotNull(message="Se debe elegir una institución", groups=AddVisit.class)
    private Institutioncampus institutioncampus;

    @NotNull(message="Se debe elegir una persona", groups=AddVisit.class)
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
