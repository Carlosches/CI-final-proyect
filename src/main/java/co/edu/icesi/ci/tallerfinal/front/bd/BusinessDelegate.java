package co.edu.icesi.ci.tallerfinal.front.bd;

import co.edu.icesi.ci.tallerfinal.front.model.classes.*;
import jdk.jfr.consumer.RecordedStackTrace;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BusinessDelegate {
    void setRestTemplate(RestTemplate restTemplate);
    // ==========================
    // PERSON
    // ==========================

    List<Person> personFindAll();

    Person personFindById(long persId);
    // ==========================
    // INSTITUTION
    // ==========================

    List<Institution> institutionFindAll();
    Institution institutionFindById(long id);      // falta test
    Institutioncampus institutioncampusFindById(long instId);

    // ==========================
    // INSTITUTION CAMPUS
    // ==========================

    List<Institutioncampus> institutionCampusFindAll();

    // ==========================
    // VISIT
    // ==========================
    List<Visit> visitByExitDate(String date);
    List<Visit> visitByEntranceDate(String date);
    List<Person> getPersonsByVisitDate(String entranceDate, String exitDate);
    List<Visit> visitFindAll();

    Visit visitFindById(long persId);

    Visit saveVisit(Visit visit, long personId, long campusId);

    void setVisit(Visit visit);

    void deleteVisit(long visitId);
    public List<Physicalcheckup> getPychesFromVisit(long visitId);       ////////// Falta test
    // ==========================
    // PhysicalCheckup
    // ==========================

    List<Physicalcheckup> physicalcheckupsFindAll();

    Physicalcheckup physicalcheckupsFindById(long id);

    Physicalcheckup savePhysicalcheckup(Physicalcheckup pc, long persId, long visitId);

    void setPhysicalcheckup(Physicalcheckup pc);

    void deletePhysicalcheckup(long phycheId);
    public List<CheckMeasur> getCheckMeasureFromPyche(long pycheId);

    // ==========================
    // MEASUREMENT
    // ==========================

    List<Measurement> measurementFindAll();

    Measurement measurementFindById(long measId);

    Measurement saveMeasurement(Measurement measurement, long instId);

    void setMeasurement(Measurement measurement);

    void deleteMeasurement(long measId);
    public List<CheckMeasur> getCheckMeasureFromMeas(long measId);

    // ==========================
    // CHECK MEASURES
    // ==========================

    List<CheckMeasur> checkMeasurFindAll();

    boolean checkMeasurExistById(CheckMeasurPK checkMeasurePK);    //falta test

    CheckMeasur checkMeasurFindById(long phycheId, long measId);

    CheckMeasur saveCheckMeasur(CheckMeasur checkMeasur, long measId, long phycheId);

    void setCheckMeasur(CheckMeasur checkMeasur);

    void deleteCheckMeasur(CheckMeasur checkMeasur);

    // ==========================
    // NEXUS POLL
    // ==========================

    List<Nexuspoll> nexusPollFindAll();

    Nexuspoll addNexusPoll(Nexuspoll nexuspoll);

    Nexuspoll nexusPollFindById(long id);

    Nexuspoll updateNexusPoll(Nexuspoll nexuspoll);

    void deleteNexusPoll(Nexuspoll nexuspoll);

    // ==========================
    // NEXUS POLL QUESTIONS
    // ==========================

    List<Nexusquestion> nexusQuestionsFindAll();

    Nexusquestion addNexusPollQuestion(Nexusquestion nexuspollQuestion);

    Nexusquestion nexusPollQuestionFindById(long id);

    Nexusquestion updateNexusPollQuestion(Nexusquestion nexuspollquestion);

    void deleteNexusQuestion(Nexusquestion nexusquestion);



}
