package co.edu.icesi.ci.tallerfinal.front.bd;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Institutioncampus;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Person;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;

import java.util.List;

public interface BusinessDelegate {

    // PERSON

    List<Person> personFindAll();

    // INSTITUTION CAMPUS

    List<Institutioncampus> institutionCampusFindAll();

    // VISIT

    List<Visit> visitFindAll();

    Visit visitFindById(long persId);

    Visit saveVisit(Visit visit, long personId, long campusId);

    void deleteVisit(long visitId);
}
