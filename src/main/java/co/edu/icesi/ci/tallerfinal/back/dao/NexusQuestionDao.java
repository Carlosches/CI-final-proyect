package co.edu.icesi.ci.tallerfinal.back.dao;

import co.edu.icesi.ci.tallerfinal.back.model.Nexusquestion;

import java.util.List;

public interface NexusQuestionDao {

    List<Nexusquestion> findAllNexusQuestions();

    void delete(Nexusquestion nexusPollQuestion);

    Nexusquestion update(Nexusquestion nexusPollQuestion);

    Nexusquestion save(Nexusquestion nexusPollQuestion);

    Nexusquestion findById(long nexpollQuestionId);

}
