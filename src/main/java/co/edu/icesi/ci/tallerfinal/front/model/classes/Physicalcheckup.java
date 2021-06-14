package co.edu.icesi.ci.tallerfinal.front.model.classes;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class Physicalcheckup {

    private static final long serialVersionUID = 1L;

    private long phycheId;

    private Date phycheDate;

    private List<CheckMeasur> checkMeasurs;

    private Person person;

    private Visit visit;

    public Physicalcheckup() {
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
