package org.ats.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.ats.entities.Job;
import org.ats.utils.DbContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;


public class JobDaoImpl implements JobDao {

    private EntityManager entityManager;

    public JobDaoImpl() {
        entityManager = DbContext.getEntityManager();
    }

    @Override
    public Job createJob(Job job) {

        // Try - resources
        Transaction tx = null;
        entityManager = DbContext.getEntityManager();

        try (Session session = entityManager.unwrap(Session.class);) {
            tx = session.getTransaction();

            tx.begin();

            session.persist(job);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }

        return job;
    }

    @Override
    public boolean isExisted(String title) {
        try (Session session = entityManager.unwrap(Session.class);) {
            TypedQuery<Long> query = session.createQuery("SELECT COUNT(j) FROM Job j " +
                    "WHERE j.title = :param", Long.class);
            query.setParameter("param", title);

            Long result = query.getSingleResult();
            return result > 0;
        }
    }
}
