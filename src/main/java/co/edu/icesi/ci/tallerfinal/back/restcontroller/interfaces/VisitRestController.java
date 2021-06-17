package co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces;

import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;

public interface VisitRestController {

    public Iterable<Visit> getVisits();
    public void saveVisit(Visit visit, long personId, long campusId);
    public void updateVisit(Visit visit);
    public void deleteVisit(long id);
    public Iterable<Physicalcheckup> getPychesFromVisit(long visitId);
}
