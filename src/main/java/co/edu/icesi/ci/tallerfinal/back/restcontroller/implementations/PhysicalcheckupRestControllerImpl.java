package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.PhysicaclchekcupRestController;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurService;
import co.edu.icesi.ci.tallerfinal.back.service.PhysicalcheckupService;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api")
public class PhysicalcheckupRestControllerImpl implements PhysicaclchekcupRestController {

    private PhysicalcheckupService physicalcheckupService;
    private CheckMeasurService checkMeasurService;
    public PhysicalcheckupRestControllerImpl(PhysicalcheckupService physicalcheckupService, CheckMeasurService checkMeasurService) {
        this.physicalcheckupService = physicalcheckupService;
        this.checkMeasurService = checkMeasurService;
    }
    @Override
    @GetMapping("/phycheckups/")
    public Iterable<Physicalcheckup> getPhyches(){
        return physicalcheckupService.findAll();
    }

    @Override
    @PostMapping("/phycheckups/")
    public void savePhyche(Physicalcheckup phyche, long personId, long visitId){
        physicalcheckupService.addPhysicalcheckup(phyche, personId, visitId);
    }
    @Override
    @PutMapping("/phycheckups/")
    public void updatePhyche(Physicalcheckup phyche){
        physicalcheckupService.editPhysicalcheckup(phyche);
    }


    @GetMapping("/phycheckups/{pycheId}")
    public Physicalcheckup physicalcheckupsFindById(@PathVariable("pycheId") long pycheId) {
        return physicalcheckupService.getPhysicalcheckup(pycheId);
    }
    @Override
    @GetMapping("/phycheckups/checkmeasures/{pycheId}")
    public Iterable<CheckMeasur> getCheckMeasures(@PathVariable("pycheId") long pycheId) {
        return checkMeasurService.findByPycheId(pycheId);
    }


}
