package isel.sisinf.grp3.logic.DBAccess;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public class JPAContext implements Context{

    private EntityManagerFactory emf;
    private EntityManager em;

    private EntityTransaction tx;
    private int txCount;

    protected List helperQueryImpl(String jpql, Object... params)
    {
        Query q = em.createQuery(jpql);

        for(int i = 0; i < params.length; ++i)
            q.setParameter(i+1, params[i]);

        return q.getResultList();
    }


    @Override
    public void beginTransaction() {
         if (tx == null){
              tx = em.getTransaction();
              tx.begin();
              txCount = 0;
         }
    }

    @Override
    public void commit() {
        --txCount;
        if (txCount == 0 & tx != null){
            tx.commit();
            tx = null;
        }
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public void close() throws Exception {
        if (tx != null)
            tx.rollback();
        em.close();
        emf.close();
    }
}
