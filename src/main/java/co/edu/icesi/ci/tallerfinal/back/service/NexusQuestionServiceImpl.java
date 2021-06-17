package co.edu.icesi.ci.tallerfinal.back.service;

import co.edu.icesi.ci.tallerfinal.back.dao.NexusPollDao;
import co.edu.icesi.ci.tallerfinal.back.dao.NexusQuestionDao;
import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.back.model.Nexusquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NexusQuestionServiceImpl implements NexusQuestionService{

    private NexusQuestionDao nexusQuestionDao;

    @Autowired
    public NexusQuestionServiceImpl(NexusQuestionDao nexusQuestionDao) {
        this.nexusQuestionDao = nexusQuestionDao;
    }

    @Override
    @Transactional
    public List<Nexusquestion> findAll() {
        return nexusQuestionDao.findAllNexusQuestions();
    }

    @Override
    @Transactional
    public void delete(Nexusquestion nexusPollQuestion){
        nexusQuestionDao.delete(nexusPollQuestion);
    }

    @Override
    @Transactional
    public Nexusquestion update(Nexusquestion nexusPollQuestion){
        return nexusQuestionDao.update(nexusPollQuestion);
    }

    @Override
    @Transactional
    public Nexusquestion save(Nexusquestion nexusPollQuestion){
        return nexusQuestionDao.save(nexusPollQuestion);
    }

    @Override
    @Transactional
    public Nexusquestion findById(long nexpollquestId){
        return nexusQuestionDao.findById(nexpollquestId);
    }

}
