package co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces;

import co.edu.icesi.ci.tallerfinal.back.model.Nexusquestion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface NexusQuestionRestController {

    Iterable<Nexusquestion> getNexusPollQuestion();

    Nexusquestion postNexusPoll(@RequestBody Nexusquestion nexusquestion);

    Nexusquestion findById(@PathVariable("id") long id);

    Nexusquestion putNexusPollQuestion(@RequestBody Nexusquestion nexusquestion);

    void deleteNexusPollQUestion(@RequestBody Nexusquestion nexusquestion);

}
