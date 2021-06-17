package co.edu.icesi.ci.tallerfinal.back.dao;

import co.edu.icesi.ci.tallerfinal.back.model.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.back.model.Nexusquestion;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Scope("singleton")
public class NexusQuestionDaoImpl implements NexusQuestionDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Nexusquestion> findAllNexusQuestions() {
        String jpql = "Select a from Nexusquestion a";
        return 	entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public void delete(Nexusquestion nexusPollQuestion) {
        entityManager.remove(nexusPollQuestion);
    }

    @Override
    public Nexusquestion update(Nexusquestion nexusPollQuestion) {
        entityManager.merge(nexusPollQuestion);
        return nexusPollQuestion;
    }

    @Override
    public Nexusquestion save(Nexusquestion nexusPollQuestion) {
        entityManager.persist(nexusPollQuestion);
        return nexusPollQuestion;
    }

    @Override
    public Nexusquestion findById(long nexpollQuestionId) {
        String jpql = "select v from Nexusquestion v where v.nexquesId=:id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", nexpollQuestionId);
        Nexusquestion pool = null;
        try {
            pool = (Nexusquestion) query.getSingleResult();
        }catch(NoResultException e) {

        }
        return pool;
    }

}
