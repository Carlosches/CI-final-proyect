package co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces;

import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface NexusPollRestController {

    Iterable<Nexuspoll> getNexusPoll();

    Nexuspoll postNexusPoll(@RequestBody Nexuspoll nexuspoll);

    Nexuspoll findById(@PathVariable("id") long id);

    Nexuspoll putNexusPoll(@RequestBody Nexuspoll nexuspoll);

    void deleteNexusPoll(@PathVariable("id") long id);

}
