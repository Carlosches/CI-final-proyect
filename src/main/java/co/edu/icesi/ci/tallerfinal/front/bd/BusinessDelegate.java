package co.edu.icesi.ci.tallerfinal.front.bd;

import co.edu.icesi.ci.tallerfinal.front.model.classes.*;

import java.util.List;

public interface BusinessDelegate {

    // ==========================
    // PERSON
    // ==========================

    List<Person> personFindAll();

    // ==========================
    // INSTITUTION
    // ==========================

    List<Institution> institutionFindAll();

    // ==========================
    // INSTITUTION CAMPUS
    // ==========================

    List<Institutioncampus> institutionCampusFindAll();

    // ==========================
    // VISIT
    // ==========================

    List<Visit> visitFindAll();

    Visit visitFindById(long persId);

    Visit saveVisit(Visit visit, long personId, long campusId);

    void setVisit(Visit visit);

    void deleteVisit(long visitId);

    // ==========================
    // PhysicalCheckup
    // ==========================

    List<Physicalcheckup> physicalcheckupsFindAll();

    Physicalcheckup physicalcheckupsFindById(long id);

    Physicalcheckup savePhysicalcheckup(Physicalcheckup pc, long persId, long visitId);

    void setPhysicalcheckup(Physicalcheckup pc);

    void deletePhysicalcheckup(long phycheId);

    // ==========================
    // MEASUREMENT
    // ==========================

    List<Measurement> measurementFindAll();

    Measurement measurementFindById(long measId);

    Measurement saveMeasurement(Measurement measurement, long instId);

    void setMeasurement(Measurement measurement);

    void deleteMeasurement(long measId);

    // ==========================
    // CHECK MEASURES
    // ==========================

    List<CheckMeasur> checkMeasurFindAll();

    boolean checkMeasurExistById(CheckMeasurPK checkMeasurePK);

    CheckMeasur CheckMeasurFindById(CheckMeasurPK checkMeasurPK);

    CheckMeasur saveCheckMeasur(CheckMeasur checkMeasur, long measId, long phycheId);

    void setCheckMeasur(CheckMeasur checkMeasur);

    void deleteCheckMeasur(CheckMeasur checkMeasur);

    // ==========================
    // NEXUS POLL
    // ==========================

    List<Nexuspoll> nexusPollFindAll();

}
