package co.edu.icesi.ci.tallerfinal.back.restcontroller;

import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.service.VisitService;
import org.springframework.web.bind.annotation.*;

@RestController
public class VisitRestController {

    private VisitService visitService;


    public VisitRestController(VisitService visitService){
        this.visitService = visitService;
    }

    @GetMapping("/visits/")
    public Iterable<Visit> getVisits(){
        return visitService.findAll();
    }
    
    @PostMapping("/visits/")
    public void saveVisit(Visit visit, long personId, long campusId){
        visitService.addVisit(visit,personId, campusId);
    }
    @PutMapping("/visits/")
    public void updateVisit(Visit visit){
        visitService.editVisit(visit);
    }
}
