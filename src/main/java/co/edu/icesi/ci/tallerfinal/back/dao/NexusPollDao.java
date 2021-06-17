package co.edu.icesi.ci.tallerfinal.back.dao;

import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;

import java.util.List;

public interface NexusPollDao {

    List<Nexuspoll> findAllNexusPoll();

    void delete(NexusPollDao nexusPoll);

    Nexuspoll update(Nexuspoll nexusPoll);

    Nexuspoll save(Nexuspoll nexusPoll);

    Nexuspoll findById(long nexpollId);

}
