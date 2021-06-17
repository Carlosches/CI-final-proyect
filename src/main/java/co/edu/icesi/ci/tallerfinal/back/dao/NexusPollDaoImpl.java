package co.edu.icesi.ci.tallerfinal.back.dao;



import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class NexusPollDaoImpl implements NexusPollDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Nexuspoll> findAllNexusPoll() {
        String jpql = "Select a from Nexuspoll a";
        return 	entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public void delete(NexusPollDao nexusPoll) {
        entityManager.remove(nexusPoll);
    }

    @Override
    public Nexuspoll update(Nexuspoll nexusPoll) {
        entityManager.merge(nexusPoll);
        return nexusPoll;
    }

    @Override
    public Nexuspoll save(Nexuspoll nexusPoll) {
        entityManager.persist(nexusPoll);
        return nexusPoll;
    }

    @Override
    public Nexuspoll findById(long nexpollId) {
        String jpql = "select v from Nexuspoll v where v.nexpollId=:id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", nexpollId);
        Nexuspoll pool = null;
        try {
            pool = (Nexuspoll) query.getSingleResult();
        }catch(NoResultException e) {

        }
        return pool;
    }

}
