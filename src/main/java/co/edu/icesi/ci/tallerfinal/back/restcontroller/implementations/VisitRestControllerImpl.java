package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.VisitRestController;
import co.edu.icesi.ci.tallerfinal.back.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VisitRestControllerImpl implements VisitRestController {

    private VisitService visitService;

    @Autowired
    public VisitRestControllerImpl(VisitService visitService){
        this.visitService = visitService;
    }

    @Override
    @GetMapping("/visits/")
    public Iterable<Visit> getVisits(){
        return visitService.findAll();
    }


    //GET
    @GetMapping("/visits/{persId}")
    public Visit visitFindById(@PathVariable("persId") long persId){
        return visitService.getVisit(persId); // TODO
    }

    @Override
    @PostMapping("/visits/")
    public Visit saveVisit(@RequestBody Visit visit,
                          @RequestParam(value = "personId", required = true) long personId,
                          @RequestParam(value = "campusId", required = true) long campusId){

        visitService.addVisit(visit,personId, campusId);

        return new Visit(); //TODO: Change with added entity
    }

    @Override
    @PutMapping("/visits/")
    public void updateVisit(Visit visit){
        visitService.editVisit(visit);
    }

    @Override
    @DeleteMapping("/visits/{persId}")
    public void deleteVisit(@PathVariable("id") long id) {
        Visit visit = visitService.getVisit(id);
        visitService.delete(visit);
    }
}
