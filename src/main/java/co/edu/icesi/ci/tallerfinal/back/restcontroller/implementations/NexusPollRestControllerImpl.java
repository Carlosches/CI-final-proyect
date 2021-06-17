package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.NexusPollController;
import co.edu.icesi.ci.tallerfinal.back.service.NexusPollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NexusPollRestControllerImpl implements NexusPollController {

    private NexusPollService nexusPollService;

    @Autowired
    public NexusPollRestControllerImpl(NexusPollService nexusPollService){
        this.nexusPollService = nexusPollService;
    }

    @GetMapping("/nexus-poll/")
    public Iterable<Nexuspoll> getNexusPoll(){
        return nexusPollService.findAll();
    }


}
