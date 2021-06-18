package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.repositories.CampusRepository;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.VisitRestController;
import co.edu.icesi.ci.tallerfinal.back.service.PhysicalcheckupService;
import co.edu.icesi.ci.tallerfinal.back.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class VisitRestControllerImpl implements VisitRestController {

    private VisitService visitService;
    private PhysicalcheckupService physicalcheckupService;
    private PersonRepository personRepository;
    private CampusRepository campusRepository;

    public VisitRestControllerImpl(VisitService visitService, PhysicalcheckupService physicalcheckupService, PersonRepository personRepository, CampusRepository campusRepository) {
        this.visitService = visitService;
        this.physicalcheckupService = physicalcheckupService;
        this.personRepository = personRepository;
        this.campusRepository = campusRepository;
    }

    @Override
    @GetMapping("/visits/")
    public Iterable<Visit> getVisits(){
        return visitService.findAll();
    }


    //GET
    @GetMapping("/visits/{visId}")
    public Visit visitFindById(@PathVariable("visId") long visId){
      //  System.out.println("visit phyches size: " + visitService.getVisit(visId).getPhysicalcheckups().size());
        return visitService.getVisit(visId);
    }

    @Override
    @PostMapping("/visits/")
    public void saveVisit(@RequestBody Visit visit,
                                          @RequestParam(value = "personId", required = true) long personId,
                                          @RequestParam(value = "campusId", required = true) long campusId){

        visitService.addVisit(visit,personId, campusId);

        //return new Visit(); //TODO: Change with added entity
    }

    @Override
    @PutMapping("/visits/")
    public void updateVisit(@RequestBody Visit visit){
        visitService.editVisit(visit);
    }

    @Override
    @DeleteMapping("/visits/{visitId}")
    public void deleteVisit(@PathVariable("visitId") long visitId) {
        Visit visit = visitService.getVisit(visitId);
        visitService.delete(visit);
    }
    @Override
    @GetMapping("/visits/phycheckups/{visitId}")
    public Iterable<Physicalcheckup> getPychesFromVisit(@PathVariable("visitId") long visitId){
        return physicalcheckupService.findByVisitId(visitId);
    }
    @GetMapping("/visits/byexitdate/{exitDate}")
    public Iterable<Visit> getVisitByExitDate(@PathVariable("exitDate") String exitDate){
        Date exDate = null;
        try {
            exDate= new SimpleDateFormat("yyyy-MM-dd").parse(exitDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return visitService.findByExitdate(exDate);
    }


}
