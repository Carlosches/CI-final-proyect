package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.VisitRestController;
import co.edu.icesi.ci.tallerfinal.back.service.VisitService;
import org.springframework.web.bind.annotation.*;

@RestController
public class VisitRestControllerImpl implements VisitRestController {

    private VisitService visitService;


    public VisitRestControllerImpl(VisitService visitService){
        this.visitService = visitService;
    }
    @Override
    @GetMapping("/visits/")
    public Iterable<Visit> getVisits(){
        return visitService.findAll();
    }
    @Override
    @PostMapping("/visits/")
    public void saveVisit(Visit visit, long personId, long campusId){
        visitService.addVisit(visit,personId, campusId);
    }
    @Override
    @PutMapping("/visits/")
    public void updateVisit(Visit visit){
        visitService.editVisit(visit);
    }
}
