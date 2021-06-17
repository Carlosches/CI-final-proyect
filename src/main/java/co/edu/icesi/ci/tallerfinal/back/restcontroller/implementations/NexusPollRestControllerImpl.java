package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.NexusPollController;
import co.edu.icesi.ci.tallerfinal.back.service.NexusPollService;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/nexus-poll/")
    public Nexuspoll postNexusPoll(@RequestBody Nexuspoll nexuspoll){
        return nexusPollService.save(nexuspoll);
    }

    @GetMapping("/nexus-poll/{id}")
    public Nexuspoll findById(@PathVariable("id") long id){
        return nexusPollService.findById(id);
    }

    @PutMapping("/nexus-poll/")
    public Nexuspoll putNexusPoll(@RequestBody Nexuspoll nexuspoll){
        return nexusPollService.update(nexuspoll);
    }

}
