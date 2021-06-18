package co.edu.icesi.ci.tallerfinal.front.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Physicalcheckup {

    private static final long serialVersionUID = 1L;

    private long phycheId;

    @NotNull(message="se debe seleccionar una fecha", groups= AddPhycheckup.class)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message="La fecha debe ser en el futuro", groups=AddPhycheckup.class)
    private Date phycheDate;

    @JsonIgnore
    private List<CheckMeasur> checkMeasurs;

    @NotNull(message="se debe seleccionar una persona", groups=AddPhycheckup.class)
    private Person person;

    @NotNull(message="se debe seleccionar una visita", groups=AddPhycheckup.class)
    private Visit visit;

    public Physicalcheckup() {
        this.checkMeasurs = new ArrayList<>();
    }

    public long getPhycheId() {
        return this.phycheId;
    }

    public void setPhycheId(long phycheId) {
        this.phycheId = phycheId;
    }

    public Date getPhycheDate() {
        return this.phycheDate;
    }

    public void setPhycheDate(Date phycheDate) {
        this.phycheDate = phycheDate;
    }

    public List<CheckMeasur> getCheckMeasurs() {
        return this.checkMeasurs;
    }

    public void setCheckMeasurs(List<CheckMeasur> checkMeasurs) {
        this.checkMeasurs = checkMeasurs;
    }

    public CheckMeasur addCheckMeasur(CheckMeasur checkMeasur) {
        getCheckMeasurs().add(checkMeasur);
        checkMeasur.setPhysicalcheckup(this);

        return checkMeasur;
    }

    public CheckMeasur removeCheckMeasur(CheckMeasur checkMeasur) {
        getCheckMeasurs().remove(checkMeasur);
        checkMeasur.setPhysicalcheckup(null);

        return checkMeasur;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Visit getVisit() {
        return this.visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

}
