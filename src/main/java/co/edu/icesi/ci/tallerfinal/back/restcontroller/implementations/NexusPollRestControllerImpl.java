package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.NexusPollRestController;
import co.edu.icesi.ci.tallerfinal.back.service.NexusPollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NexusPollRestControllerImpl implements NexusPollRestController {

    private NexusPollService nexusPollService;

    @Autowired
    public NexusPollRestControllerImpl(NexusPollService nexusPollService){
        this.nexusPollService = nexusPollService;
    }

    @Override
    @GetMapping("/nexus-poll/")
    public Iterable<Nexuspoll> getNexusPoll(){
        return nexusPollService.findAll();
    }

    @Override
    @PostMapping("/nexus-poll/")
    public Nexuspoll postNexusPoll(@RequestBody Nexuspoll nexuspoll){
        return nexusPollService.save(nexuspoll);
    }

    @Override
    @GetMapping("/nexus-poll/{id}")
    public Nexuspoll findById(@PathVariable("id") long id){
        return nexusPollService.findById(id);
    }

    @Override
    @PutMapping("/nexus-poll/")
    public Nexuspoll putNexusPoll(@RequestBody Nexuspoll nexuspoll){
        return nexusPollService.update(nexuspoll);
    }

    @Override
    @DeleteMapping("/nexus-poll/{id}")
    public void deleteNexusPoll(@PathVariable("id") long id){
        Nexuspoll nexuspoll = nexusPollService.findById(id);
        if(nexuspoll != null) {
            nexusPollService.delete(nexuspoll);
        }
    }

}
