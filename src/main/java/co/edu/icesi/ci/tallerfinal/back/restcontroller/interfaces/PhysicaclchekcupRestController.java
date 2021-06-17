package co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces;

import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import org.springframework.web.bind.annotation.PathVariable;

public interface PhysicaclchekcupRestController {

    public Iterable<Physicalcheckup> getPhyches();
    public void savePhyche(Physicalcheckup phyche, long personId, long visitId);
    public void updatePhyche(Physicalcheckup phyche);
    public Physicalcheckup physicalcheckupsFindById(@PathVariable("pycheId") long pycheId);
}
