package co.edu.icesi.ci.tallerfinal.back.service;

import co.edu.icesi.ci.tallerfinal.back.dao.NexusPollDao;
import co.edu.icesi.ci.tallerfinal.back.dao.NexusPollDaoImpl;
import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NexusPollServiceImpl implements NexusPollService {

    private NexusPollDao nexusPollDao;

    @Autowired
    public NexusPollServiceImpl(NexusPollDao nexusPollDao) {
        this.nexusPollDao = nexusPollDao;
    }

    @Override
    @Transactional
    public List<Nexuspoll> findAll() {
        return nexusPollDao.findAllNexusPoll();
    }

    @Override
    @Transactional
    public void delete(Nexuspoll nexusPoll){
        nexusPollDao.delete(nexusPoll);
    }

    @Override
    @Transactional
    public Nexuspoll update(Nexuspoll nexuspoll){
        return nexusPollDao.update(nexuspoll);
    }

    @Override
    @Transactional
    public Nexuspoll save(Nexuspoll nexuspoll){
        return nexusPollDao.save(nexuspoll);
    }

    @Override
    @Transactional
    public Nexuspoll findById(long nexpollId){
        return nexusPollDao.findById(nexpollId);
    }

}
