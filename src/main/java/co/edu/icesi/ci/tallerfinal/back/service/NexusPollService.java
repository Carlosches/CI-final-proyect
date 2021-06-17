package co.edu.icesi.ci.tallerfinal.back.service;

import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;

import java.util.List;

public interface NexusPollService {

    List<Nexuspoll> findAll();

    void delete(Nexuspoll nexusPoll);

    Nexuspoll update(Nexuspoll nexuspoll);

    Nexuspoll save(Nexuspoll nexuspoll);

    Nexuspoll findById(long nexpollId);

}
