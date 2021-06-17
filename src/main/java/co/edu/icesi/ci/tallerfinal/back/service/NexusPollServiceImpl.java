package co.edu.icesi.ci.tallerfinal.back.service;

import co.edu.icesi.ci.tallerfinal.back.dao.NexusPollDao;
import co.edu.icesi.ci.tallerfinal.back.dao.NexusPollDaoImpl;
import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NexusPollServiceImpl implements NexusPollService{

    private NexusPollDaoImpl nexusPollDao;


    public List<Nexuspoll> findAll() {
        return nexusPollDao.findAllNexusPoll();
    }

    public void delete(Nexuspoll nexusPoll){
        nexusPollDao.delete(nexusPollDao);
    }

    public Nexuspoll update(Nexuspoll nexuspoll){
        return nexusPollDao.update(nexuspoll);
    }

    public Nexuspoll save(Nexuspoll nexuspoll){
        return nexusPollDao.save(nexuspoll);
    }

    public Nexuspoll findById(long nexpollId){
        return nexusPollDao.findById(nexpollId);
    }


}
