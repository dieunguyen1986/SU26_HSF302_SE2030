package org.ats.dao;

import jakarta.persistence.EntityManager;
import org.ats.entities.Job;
import org.ats.utils.DbContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JobDaoImpl implements JobDao {

    private EntityManager entityManager;

    public JobDaoImpl() {
        entityManager = DbContext.getEntityManager();
    }

    @Override
    public Job createJob(Job job) {

        // Try - resources
        Transaction tx = null;
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
}
