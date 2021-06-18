package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.back.model.Nexusquestion;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.NexusQuestionRestController;
import co.edu.icesi.ci.tallerfinal.back.service.NexusPollService;
import co.edu.icesi.ci.tallerfinal.back.service.NexusQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NexusQuestionRestControllerImpl implements NexusQuestionRestController {

    private NexusQuestionService nexusQuestionService;

    @Autowired
    public NexusQuestionRestControllerImpl(NexusQuestionService nexusQuestionService){
        this.nexusQuestionService = nexusQuestionService;
    }

    @Override
    @GetMapping("/nexus-quest/")
    public Iterable<Nexusquestion> getNexusPollQuestion(){
        return nexusQuestionService.findAll();
    }

    @Override
    @PostMapping("/nexus-quest/")
    public Nexusquestion postNexusPoll(@RequestBody Nexusquestion nexusquestion){
        return nexusQuestionService.save(nexusquestion);
    }

    @Override
    @GetMapping("/nexus-quest/{id}")
    public Nexusquestion findById(@PathVariable("id") long id){
        return nexusQuestionService.findById(id);
    }

    @Override
    @PutMapping("/nexus-quest/")
    public Nexusquestion putNexusPollQuestion(@RequestBody Nexusquestion nexusquestion){
        return nexusQuestionService.update(nexusquestion);
    }

    @Override
    @DeleteMapping("/nexus-quest/")
    public void deleteNexusPollQUestion(@RequestBody Nexusquestion nexusquestion){
        nexusQuestionService.delete(nexusquestion);
    }

}
