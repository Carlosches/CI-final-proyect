package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.PhysicaclchekcupRestController;
import co.edu.icesi.ci.tallerfinal.back.service.PhysicalcheckupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhysicalcheckupRestControllerImpl implements PhysicaclchekcupRestController {

    private PhysicalcheckupService physicalcheckupService;

    public PhysicalcheckupRestControllerImpl(PhysicalcheckupService physicalcheckupService) {
        this.physicalcheckupService = physicalcheckupService;
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

}
