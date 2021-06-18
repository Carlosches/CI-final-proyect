package co.edu.icesi.ci.tallerfinal.back.service;

import co.edu.icesi.ci.tallerfinal.back.model.Nexusquestion;

import java.util.List;

public interface NexusQuestionService {

    List<Nexusquestion> findAll();

    void delete(Nexusquestion nexusPollQuestion);

    Nexusquestion update(Nexusquestion nexusPollQuestion);

    Nexusquestion save(Nexusquestion nexusPollQuestion);

    Nexusquestion findById(long nexpollquestId);

}
